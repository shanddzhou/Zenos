package com.zhoushan.blog.business.service;

import java.util.Map;

public interface ShiroService {
    Map<String, String> loadFilterChainDefinitions();

    void updatePermission();

    void reloadAuthorizingByRoleId(Long roleId);
}
