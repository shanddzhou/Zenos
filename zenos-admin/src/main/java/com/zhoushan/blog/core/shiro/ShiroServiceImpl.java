package com.zhoushan.blog.core.shiro;

import com.zhoushan.blog.business.entity.Resources;
import com.zhoushan.blog.business.entity.User;
import com.zhoushan.blog.business.service.SysResourcesService;
import com.zhoushan.blog.business.service.SysUserService;
import com.zhoushan.blog.core.shiro.realm.shiroRealm.ShiroRealm;
import com.zhoushan.blog.framework.exception.ZenosException;
import com.zhoushan.blog.framework.holder.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhooo
 * @version V1.0
 * @Title: ShiroServiceImpl
 * @Package com.zhoushan.blog.business.service.impl
 * @Description: TODO
 * @date 2019/7/7 20:18
 */
@Slf4j
@Service
public class ShiroServiceImpl implements ShiroService {
    @Autowired
    private SysResourcesService sysResourcesService;
    @Autowired
    private SysUserService sysUserService;

    /**
     * 初始化权限
     *
     * @return
     */
    @Override
    public Map<String, String> loadFilterChainDefinitions() {
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //配置filter过滤器
        filterChainDefinitionMap.put("/passport/logout", "logout");
        filterChainDefinitionMap.put("passport/login", "anon");
        filterChainDefinitionMap.put("passprot/signin", "anon");
        filterChainDefinitionMap.put("websocket", "anon");
        filterChainDefinitionMap.put("/favicon", "anon");
        filterChainDefinitionMap.put("/error", "anon");
        filterChainDefinitionMap.put("assets/**", "anon");
        filterChainDefinitionMap.put("/plugin/**", "anon");
        filterChainDefinitionMap.put("/vendors/**", "anon");
        filterChainDefinitionMap.put("/getKaptcha", "anon");
        //加载数据库中配置的资源权限
        List<Resources> resourcesList = sysResourcesService.listUrlAndPermission();
        if (CollectionUtils.isEmpty(resourcesList)) {
            throw new ZenosException("未加载到resource的内容，请查询是否初始化数据库");
        }
        for (Resources resources : resourcesList) {
            if (!StringUtils.isEmpty(resources.getUrl()) && !StringUtils.isEmpty(resources.getPermission())) {
                String permission = "perms[" + resources.getPermission() + "]";
                filterChainDefinitionMap.put(resources.getUrl(), permission);
            }
        }
        filterChainDefinitionMap.put("/**", "user");
        return filterChainDefinitionMap;
    }

    /**
     * 重新加载权限
     */
    @Override
    public void updatePermission() {
        ShiroFilterFactoryBean shiroFilter = SpringContextHolder.getBean(ShiroFilterFactoryBean.class);
        synchronized (shiroFilter) {
            AbstractShiroFilter shiroFilter1 = null;
            try {
                shiroFilter1 = (AbstractShiroFilter) shiroFilter.getObject();
            } catch (Exception e) {
                throw new RuntimeException("get ShiroFilter from shiroFilterFactoryBean error!");
            }
            PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter1.getFilterChainResolver();
            DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();

            //清空原始权限
            manager.getFilterChains().clear();

            shiroFilter.getFilterChainDefinitionMap().clear();
            shiroFilter.setFilterChainDefinitionMap(loadFilterChainDefinitions());

            //重新加载权限
            Map<String, String> chains = shiroFilter.getFilterChainDefinitionMap();
            for (Map.Entry<String, String> entry : chains.entrySet()) {
                String url = entry.getKey();
                String chainDefinition = entry.getValue().trim().replace(" ", "");
                manager.createChain(url, chainDefinition);
            }
        }
    }

    /**
     * 重新加载所有拥有roleId角色的权限
     * @param roleId
     */
    @Override
    public void reloadAuthorizingByRoleId(Long roleId) {
        List<User> userList = sysUserService.listByRoleId(roleId);
        if (CollectionUtils.isEmpty(userList)) {
            return;
        }
        for (User user : userList) {
            reloadAuthorizingByUser(user);
        }
    }

    private void reloadAuthorizingByUser(User user) {
        RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        ShiroRealm shiroRealm = (ShiroRealm) rsm.getRealms().iterator().next();
        Subject subject = SecurityUtils.getSubject();
        String realmName = subject.getPrincipals().getRealmNames().iterator().next();
        SimplePrincipalCollection principals = new SimplePrincipalCollection(user.getId(), realmName);
        subject.runAs(principals);
        shiroRealm.getAuthenticationCache().remove(subject.getPrincipals());

        log.info("用户[{}]的权限更新成功！！", user.getUsername());
    }


}
