package com.zhoushan.blog.framework.config;

import org.springframework.stereotype.Component;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author zhooo
 * @version V1.0
 * @Title: MybatisConfig
 * @Package com.zhoushan.blog.framework.config
 * @Description:MybatisConfig
 * @date 7/14/2019 10:30 PM
 */
@Component
@MapperScan(value = "com.zhoushan.blog.persistence.mapper")
public class MybatisConfig {
}
