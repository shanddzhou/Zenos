package com.zhoushan.blog.utils;

import com.zhoushan.blog.framework.holder.RequestHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhooo
 * @version V1.0
 * @Title: RequestUtill
 * @Package com.zhoushan.blog.utils
 * @Description: 关于Request请求相关工具方法
 * @date 2019/7/9 14:32
 */
public class RequestUtill {
    /**
     * 获取request请求参数
     * @return String数据
     */
    public static String getParameters() {
        HttpServletRequest request = RequestHolder.getRequest();
        if (null == request) {
            return null;
        }
        Enumeration<String> requestParameterNames = request.getParameterNames();
        if (null == requestParameterNames) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        while (requestParameterNames.hasMoreElements()) {
            String nextElement = requestParameterNames.nextElement();
            stringBuffer.append("&").append(nextElement).append("=").append(request.getParameter(nextElement));
        }
        return stringBuffer.toString();
    }

    /**
     * 获取request的参数
     * @return Map对象
     */
    public static Map<String, Object> getParametersMap() {
        HttpServletRequest request = RequestHolder.getRequest();
        Map<String,Object> hashMap = new HashMap<>();
        if (null == request) {
            return hashMap;
        }
        Enumeration<String> parameterNames = request.getParameterNames();
        if (null == request) {
            return hashMap;
        }
        while (parameterNames.hasMoreElements()) {
            String nextElement = parameterNames.nextElement();
            hashMap.put(nextElement, request.getParameter(nextElement));
        }
        return hashMap;
    }

    /**
     * 根据headerName获取参数值
     * @param headerName Key
     * @return
     */
    public static String getHeader(String headerName) {
        HttpServletRequest request = RequestHolder.getRequest();
        if (null == request) {
            return null;
        }
        String header = request.getHeader(headerName);
        return header;
    }

    public static String getUa() {
        return getHeader("User-Agent");
    }

    public static String getReferer() {
        return getHeader("Referer");
    }
    public static String getIp() {
        HttpServletRequest request = RequestHolder.getRequest();
        if (null == request) {
            return null;
        }
        return IpUtil.getRealIp(request);
    }

    public static String getRequestUrl() {
        HttpServletRequest request = RequestHolder.getRequest();
        if (null == request) {
            return null;
        }
        return request.getRequestURL().toString();
    }

    public static String getMethod() {
        HttpServletRequest request = RequestHolder.getRequest();
        if (null == request) {
            return null;
        }
        return request.getMethod();
    }

    public static boolean isAjax(HttpServletRequest request) {
        if (null == request) {
            request = RequestHolder.getRequest();
        }
        if (null == request) {
            return false;
        }
        return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))
                || request.getParameter("ajax") != null;

    }
}
