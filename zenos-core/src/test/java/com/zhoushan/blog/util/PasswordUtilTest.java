package com.zhoushan.blog.util;

import org.junit.Test;

/**
 * @author zhooo
 * @version V1.0
 * @Title: PasswordUtilTest
 * @Package com.zhoushan.blog.util
 * @Description: TODO
 * @date 2019/7/7 13:11
 */
public class PasswordUtilTest {
    @Test
    public void test1() {
        String root = PasswordUtil.encrypt("123123", "root");
        System.out.println(root);
        String decrypt = PasswordUtil.decrypt("b5cf62d56974135475b091a8f2ab8431","root");
        System.out.println(decrypt);
    }
}
