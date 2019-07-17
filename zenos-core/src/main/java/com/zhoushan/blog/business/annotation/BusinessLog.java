package com.zhoushan.blog.business.annotation;

import com.zhoushan.blog.business.enums.PlatformEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhooo
 * @version V1.0
 * @Title: BusinessLog
 * @Package com.zhoushan.blog.business.annotation
 * @Description: TODO
 * @date 7/11/2019 9:08 PM
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface BusinessLog {
    /**
     * 业务名称
     */
    String value() default "";

    /**
     * 默认平台
     */
    PlatformEnum plateform() default PlatformEnum.ADMIN;

    /**
     * 是否保存数据库
     */
    boolean save() default true;

}
