package com.zhoushan.blog.business.service.impl;

import com.zhoushan.blog.business.service.SysResourcesService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhooo
 * @version V1.0
 * @Title: Test
 * @Package com.zhoushan.blog.business.service.impl
 * @Description: TODO
 * @date 7/14/2019 9:15 PM
 */
public class serviceTest {
    @Autowired
    private SysResourcesService sysResourcesService;
    @Test
    public void serviceImplTest() {
        System.out.println("hello world");
    }
}
