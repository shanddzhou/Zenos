package com.zhoushan.blog.business.service.impl;

import com.zhoushan.blog.business.service.SysResourcesService;
import com.zhoushan.blog.framework.property.RedisProperties;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhooo
 * @version V1.0
 * @Title: serviceTest
 * @Package com.zhoushan.blog.business.service.impl
 * @Description: TODO
 * @date 7/14/2019 9:19 PM
 */
public class serviceTest {
    @Autowired
    private SysResourcesService sysResourcesService;
    @Autowired
    private RedisProperties redisProperties;
    @Test
    public void serviceImplTest() {
        System.out.println(redisProperties.getExpire());
    }
}
