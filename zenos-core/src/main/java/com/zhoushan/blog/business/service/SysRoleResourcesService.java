package com.zhoushan.blog.business.service;

import com.zhoushan.blog.business.entity.RoleResources;
import com.zhoushan.blog.framework.object.AbstractService;

/**
 * @author zhooo
 * @version V1.0
 * @Title: SysRoleResources
 * @Package com.zhoushan.blog.business.service
 * @Description: TODO
 * @date 2019/7/7 17:54
 */
public interface SysRoleResourcesService extends AbstractService<RoleResources,Long> {
    /**
     * 添加角色资源
     *
     * @param roleId
     * @param resourcesId
     */
    void addRoleResources(Long roleId, String resourcesId);

    /**
     * 通过角色id批量删除
     *
     * @param roleId
     */
    void removeByRoleId(Long roleId);
}
