package com.zhoushan.blog.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.validation.support.BindingAwareModelMap;

/**
 * @author zhooo
 * @version V1.0
 * @Title: CacheUtil
 * @Package com.zhoushan.blog.utils
 * @Description: TODO
 * @date 7/11/2019 9:33 PM
 */
public class CacheUtil {
    public static String getMethodParamsKey(Object... params) {
        if (null == params || params.length == 0) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer("(");
        for (Object object : params) {
            if (object.getClass().equals(BindingAwareModelMap.class)) {
                continue;
            }
            stringBuffer.append(JSON.toJSONString(object).replace("\"", "'"));
        }
        stringBuffer.append(")");
        return stringBuffer.toString();
    }
}
