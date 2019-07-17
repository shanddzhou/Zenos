package com.zhoushan.blog.business.aspcet;

import com.zhoushan.blog.business.annotation.BusinessLog;
import com.zhoushan.blog.business.enums.PlatformEnum;
import com.zhoushan.blog.business.service.SysLogService;
import com.zhoushan.blog.utils.AspectUtil;
import com.zhoushan.blog.utils.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author zhooo
 * @version V1.0
 * @Title: BusinessLogAspect
 * @Package com.zhoushan.blog.business.aspcet
 * @Description: BusinessLog切面
 * @date 7/11/2019 9:11 PM
 */
@Slf4j
@Aspect
@Component
public class BusinessLogAspect {
    @Autowired
    private SysLogService sysLogService;

    @Pointcut(value = "@annotation(com.zhoushan.blog.business.annotation.BusinessLog)")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object writeLog(ProceedingJoinPoint point) throws Throwable {
        //执行业务
        Object result = point.proceed();

        try {
            handle(point);
        } catch (Exception e) {
            log.error("日志记录出现错误~", e);
        }
        return result;
    }

    public void handle(ProceedingJoinPoint point) throws Exception {
        Method method = AspectUtil.INSTANCE.getMethod(point);
        BusinessLog annotation = method.getAnnotation(BusinessLog.class);
        boolean save = annotation.save();
        PlatformEnum plateform = annotation.plateform();
        String businessName = AspectUtil.INSTANCE.parseParams(point.getArgs(), annotation.value());
        String ua = RequestUtil.getUa();
        log.info("{} | {} - {} {} - {}", businessName, RequestUtil.getIp(), RequestUtil.getMethod(), RequestUtil.getRequestUrl(), ua);
        if (!save) {
            return;
        }
        sysLogService.asyncAddSystemLog(plateform, businessName);
    }
}
