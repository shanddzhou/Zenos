package com.zhoushan.blog.utils;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhooo
 * @version V1.0
 * @Title: IpUtil
 * @Package com.zhoushan.blog.utils
 * @Description: TODO
 * @date 2019/7/9 14:48
 */
public class IpUtil {

    /**
     * 获取真实IP
     *
     * @param request
     * @return
     */
    public static String getRealIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        return checkIp(ip) ? ip : (
                checkIp(ip = request.getHeader("Proxy-Client-IP")) ? ip : (
                        checkIp(ip = request.getHeader("WL-Proxy-Client-IP")) ? ip :
                                request.getRemoteAddr()));
    }

    /**
     * 校验IP
     *
     * @param ip
     * @return
     */
    private static boolean checkIp(String ip) {
        return !StringUtils.isEmpty(ip) && !"unknown".equalsIgnoreCase(ip);
    }
}