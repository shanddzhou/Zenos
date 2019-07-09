package com.zhoushan.blog.core.shiro.realm.shiroRealm;

import com.zhoushan.blog.business.entity.Resources;
import com.zhoushan.blog.business.entity.Role;
import com.zhoushan.blog.business.entity.User;
import com.zhoushan.blog.business.enums.UserStatusEnum;
import com.zhoushan.blog.business.enums.UserTypeEnum;
import com.zhoushan.blog.business.service.SysResourcesService;
import com.zhoushan.blog.business.service.SysRoleService;
import com.zhoushan.blog.business.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zhooo
 * @version V1.0
 * @Title: shiroRealm
 * @Package com.zhoushan.blog.core.shiro.realm.shiroRealm
 * @Description: TODO
 * @date 2019/7/7 19:52
 */
public class ShiroRealm extends AuthorizingRealm {
    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysResourcesService sysResourcesService;
    @Resource
    private SysRoleService sysRoleService;

    /**
     * 提供账户信息返回认证信息（用户的角色信息集合）
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        User user = sysUserService.getByUserName(username);
        if (user == null) {
            throw new UnknownAccountException("账户不存在");
        }
        if (user.getStatus() != null && UserStatusEnum.DISABLE.getCode().equals(user.getStatus())) {
            throw new LockedAccountException("账户已经被锁定，禁止登陆");
        }

        return new SimpleAuthenticationInfo(
                user.getId(),
                user.getPassword(),
                ByteSource.Util.bytes(username),
                getName()
        );
    }
    /**
     * 权限认证，为当前登录的Subject授予角色和权限（角色的权限信息集合）
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        Long userId = (Long) SecurityUtils.getSubject().getPrincipal();

        // 赋予角色
        List<Role> roleList = sysRoleService.listRolesByUserId(userId);
        for (Role role : roleList) {
            info.addRole(role.getName());
        }

        // 赋予权限
        List<Resources> resourcesList = null;
        User user = sysUserService.getByPrimaryKey(userId);
        if (null == user) {
            return info;
        }
        // ROOT用户默认拥有所有权限
        if (UserTypeEnum.ROOT.toString().equalsIgnoreCase(user.getUserType())) {
            resourcesList = sysResourcesService.listAll();
        } else {
            resourcesList = sysResourcesService.listByUserId(userId);
        }

        if (!CollectionUtils.isEmpty(resourcesList)) {
            Set<String> permissionSet = new HashSet<>();
            for (Resources resources : resourcesList) {
                String permission = null;
                if (!StringUtils.isEmpty(permission = resources.getPermission())) {
                    permissionSet.addAll(Arrays.asList(permission.trim().split(",")));
                }
            }
            info.setStringPermissions(permissionSet);
        }
        return info;
    }


}
