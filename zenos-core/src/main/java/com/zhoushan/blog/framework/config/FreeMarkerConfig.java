package com.zhoushan.blog.framework.config;

import com.zhoushan.blog.framework.tag.CustomTags;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhooo
 * @version V1.0
 * @Title: FreeMarkerConfig
 * @Package com.zhoushan.blog.framework.config
 * @Description: FreeMaeker配置类
 * @date 7/14/2019 10:09 PM
 */
public class FreeMarkerConfig {
    @Autowired
    protected freemarker.template.Configuration configuration;
    @Autowired
    protected CustomTags customTags;
}
