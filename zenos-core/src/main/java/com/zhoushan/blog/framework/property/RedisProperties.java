package com.zhoushan.blog.framework.property;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * @author zhooo
 * @version V1.0
 * @Title: RedisProperties
 * @Package com.zhoushan.blog.framework.property
 * @Description: redis相关配置
 * @date 2019/7/7 18:30
 */
@Component
@ConfigurationProperties(prefix = "spring.redis")
@Data
@EqualsAndHashCode(callSuper = false)
@Order(-1)
public class RedisProperties {
    private Integer database;
    private String host;
    private Integer port;
    private String password;
    private Duration timeout;

    private Integer expire = 2592000;
}
