package com.zhoushan.blog.utils;

import cn.hutool.core.util.ReUtil;
import com.alibaba.fastjson.JSON;
import com.zhoushan.blog.framework.exception.ZenosException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author zhooo
 * @version V1.0
 * @Title: AspectUtil
 * @Package com.zhoushan.blog.utils
 * @Description: Aspect相关工具
 * @date 7/11/2019 9:19 PM
 */
public enum AspectUtil {
    INSTANCE;

    public String getKey(ProceedingJoinPoint point, String extra, String prefix) throws NoSuchMethodException {
        Method method = this.getMethod(point);
        if (null == method) {
            throw new ZenosException("错误的操作，未发现方法");
        }
        String name = method.getName();
        return getKey(point, prefix) + "_" + name + CacheUtil.getMethodParamsKey(point.getArgs()) + (null == extra ? "" : extra);

    }

    public String getKey(ProceedingJoinPoint point, String prefix) {
        String keyPrefix = "";
        if (!StringUtils.isEmpty(keyPrefix)) {
            keyPrefix += prefix;
        }
        keyPrefix += getClassName(point);
        return keyPrefix;
    }

    private String getClassName(ProceedingJoinPoint point) {
        return point.getTarget().getClass().getName().replaceAll("\\.", "_");
    }

    /**
     * 获取当前执行切点的方法名
     * @param point
     * @return
     */
    public Method getMethod(ProceedingJoinPoint point) throws NoSuchMethodException {
        Signature signature = point.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Object target = point.getTarget();
        return target.getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
    }

    public String parseParams(Object[] params, String businessName) {
        if (businessName.contains("{") && businessName.contains("}")) {
            List<String> strings = ReUtil.findAll("(?<=\\{)(\\d+)", businessName, 0);
            for (String s : strings) {
                int parseInt = Integer.parseInt(s);
                businessName = businessName.replaceAll("\\{" + parseInt + "}", JSON.toJSONString(params[parseInt - 1]));
            }
        }
        return businessName;
    }

}
