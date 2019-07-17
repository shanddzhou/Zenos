package com.zhoushan.blog.core.config;

import com.zhoushan.blog.core.interceptor.RememberAuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zhooo
 * @version V1.0
 * @Title: WebMvcConfig
 * @Package com.zhoushan.blog.core.config
 * @Description: TODO
 * @date 7/15/2019 10:21 PM
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private RememberAuthenticationInterceptor rememberAuthenticationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(rememberAuthenticationInterceptor).excludePathPatterns("/passport/**", "/error/**", "/assets/**", "/getKaptcha/**", "/websocket", "favicon.ico")
                .addPathPatterns("/**");
    }
}
