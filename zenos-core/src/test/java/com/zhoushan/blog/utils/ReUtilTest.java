package com.zhoushan.blog.utils;

import cn.hutool.core.util.ReUtil;
import org.junit.Test;

/**
 * @author zhooo
 * @version V1.0
 * @Title: ReUtilTest
 * @Package com.zhoushan.blog.utils
 * @Description: TODO
 * @date 7/12/2019 4:50 PM
 */
public class ReUtilTest {
    @Test
    public void HutoolTest() {
        int count = ReUtil.count("(?<=\\{)(\\d+)", "{1}{23}{23}{123}test");
        System.out.println(count);
//        List<String> all = ReUtil.findAll("(?<=\\{)(\\d+)", "{1}test", 0);
//        for (String s : all) {
//            System.out.println(s);
//        }
    }
}
