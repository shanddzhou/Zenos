package com.zhoushan.blog.framework.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zhooo
 * @version V1.0
 * @Title: AppProperties
 * @Package com.zhoushan.blog.framework.property
 * @Description: TODO
 * @date 2019/7/7 14:50
 */
@Component
@ConfigurationProperties(prefix = "app")
@Data
public class AppProperties {
    /**
     * 是否开启验证码功能
     */
    public boolean enableKaptcha = false;
}
