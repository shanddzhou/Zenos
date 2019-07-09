package com.zhoushan.blog.framework.property;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author zhooo
 * @version V1.0
 * @Title: ShiroProperties
 * @Package com.zhoushan.blog.framework.property
 * @Description: Shiro相关配置
 * @date 2019/7/7 18:33
 */
@Component
@ConfigurationProperties(prefix = "app.shiro")
@Data
@EqualsAndHashCode(callSuper = false)
@Order(-1)
public class ShiroProperties {
    private String loginUrl = "passport/login";
    private String successUrl = "/";
    private String unauthorizedUrl = "/error/443";
}
