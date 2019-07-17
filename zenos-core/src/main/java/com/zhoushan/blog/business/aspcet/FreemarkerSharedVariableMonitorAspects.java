package com.zhoushan.blog.business.aspcet;

import com.zhoushan.blog.business.enums.ConfigKeyEnum;
import com.zhoushan.blog.business.service.SysConfigService;
import freemarker.template.TemplateModelException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * @author zhooo
 * @version V1.0
 * @Title: FreemarkerSharedVariableMonitorAspects
 * @Package com.zhoushan.blog.business.aspcet
 * @Description: Freemarker共享变量监控切面
 * @date 7/12/2019 9:57 PM
 */
@Slf4j
@Component
@Aspect
@Order(1)
public class FreemarkerSharedVariableMonitorAspects {
    private static volatile long configLastUpdateTime = 0L;
    @Autowired
    protected freemarker.template.Configuration configuration;
    @Autowired
    protected SysConfigService sysConfigService;

    @Pointcut(value = "@annotation(org.springframework.web.bind.annotation.GetMapping)" + "||@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void pointcut() {

    }

    @After("pointcut()")
    public void after(JoinPoint point) {
        Map configs = sysConfigService.getConfigs();
        if (null == configs) {
            log.error("config为空");
            return;
        }
        Long updateTime = ((Date) configs.get(ConfigKeyEnum.UPDATE_TIME.getKey())).getTime();
        if (updateTime == configLastUpdateTime) {
            log.debug("config表未更新");
            return;
        }
        log.debug("config表已经更新，重新加载config到share variable");
        configLastUpdateTime = updateTime;
        try {
            configuration.setSharedVariable("configs", configs);
        } catch (TemplateModelException e) {
            e.printStackTrace();
        }
    }
}
