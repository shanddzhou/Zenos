package com.zhoushan.blog.framework.holder;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author zhooo
 * @version V1.0
 * @Title: SpringContextHolder
 * @Package com.zhoushan.blog.framework.holder
 * @Description: Spring容器Holder
 * @date 2019/7/7 21:15
 */
@Component
public class SpringContextHolder implements ApplicationContextAware {
    private static ApplicationContext appContext = null;

    /**
     * 通过name获取Bean
     * @param name
     * @return
     */
    public static Object getBean(String name) {
        return appContext.getBean(name);
    }

    /**
     * 通过class获取Bean
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        return appContext.getBean(clazz);
    }

    /**
     * 通过name和Class返回Bean
     * @param name
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return appContext.getBean(name, clazz);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (appContext == null) {
            appContext = applicationContext;
        }
    }
}
