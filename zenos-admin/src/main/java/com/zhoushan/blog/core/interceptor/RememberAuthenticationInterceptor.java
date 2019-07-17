package com.zhoushan.blog.core.interceptor;

import com.zhoushan.blog.business.consts.SessionConst;
import com.zhoushan.blog.business.entity.User;
import com.zhoushan.blog.business.service.SysUserService;
import com.zhoushan.blog.utils.PasswordUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhooo
 * @version V1.0
 * @Title: RembmerAuthenticationInterception
 * @Package com.zhoushan.blog.core.interceptor
 * @Description: TODO
 * @date 7/15/2019 10:12 PM
 */
@Slf4j
@Component
public class RememberAuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    private SysUserService sysUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return true;
        }
        Session session = subject.getSession(true);
        if (session.getAttribute(SessionConst.USER_SESSION_KEY) != null) {
            return true;
        }
        if (!subject.isRemembered()) {
            log.warn("未设置记住我，跳转到登录页");
            response.sendRedirect(request.getContextPath() + "/passport/login");
            return false;
        }
        try {
            long userId = Long.parseLong(subject.getPrincipals().toString());
            User user = sysUserService.getByPrimaryKey(userId);
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), PasswordUtil.decrypt(user.getPassword(), user.getUsername()), true);
            subject.login(token);
            session.setAttribute(SessionConst.USER_SESSION_KEY, user);
            log.info("[{}] - 已自动登录", user.getUsername());
        } catch (Exception e) {
            log.error("自动登录失败", e);
            response.sendRedirect(request.getContextPath() + "/passport/login");
            return false;
        }
        return true;
    }
}
