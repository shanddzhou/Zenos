package com.zhoushan.blog.business.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface RedisCache {
    /**
     * 业务名称
     */
    String value() default "";

    /**
     * redis的缓存Key
     */
    String key() default "";

    /**
     * 刷新缓存
     */
    boolean flush() default false;

    /**
     * 缓存失效时间默认30天
     */
    long expire() default 30L;

    /**
     * 缓存时间默认为天
     */
    TimeUnit unit() default TimeUnit.DAYS;

    /**
     * 是否开启缓存
     */
    boolean enable() default true;
}
