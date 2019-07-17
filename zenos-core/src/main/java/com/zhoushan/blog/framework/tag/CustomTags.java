package com.zhoushan.blog.framework.tag;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * @author zhooo
 * @version V1.0
 * @Title: CustomTags
 * @Package com.zhoushan.blog.framework.tag.CustomTags
 * @Description: TODO
 * @date 7/14/2019 10:15 PM
 */
public class CustomTags extends BaseTag {
    private final Random randoms = new Random();
    private final DecimalFormat decimalFormat = new DecimalFormat("#.##");

    public CustomTags(String targetClassPath) {
        super(targetClassPath);
    }
}
