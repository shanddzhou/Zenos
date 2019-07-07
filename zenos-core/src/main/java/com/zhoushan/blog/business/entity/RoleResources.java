package com.zhoushan.blog.business.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zhoushan.blog.persistence.beans.SysRoleResources;

import java.util.Date;

/**
 * @author zhooo
 * @version V1.0
 * @Title: RoleResources
 * @Package com.zhoushan.blog.business.entity
 * @Description: TODO
 * @date 2019/7/7 13:01
 */
public class RoleResources {
    private SysRoleResources sysRoleResources;

    public RoleResources() {
        this.sysRoleResources = new SysRoleResources();
    }

    public RoleResources(SysRoleResources sysRoleResources) {
        this.sysRoleResources = sysRoleResources;
    }

    @JsonIgnore
    public SysRoleResources getSysRoleResources() {
        return this.sysRoleResources;
    }

    public Long getRoleId() {
        return this.sysRoleResources.getRoleId();
    }

    public void setRoleId(Long roleId) {
        this.sysRoleResources.setRoleId(roleId);
    }

    public Long getResourcesId() {
        return this.sysRoleResources.getResourcesId();
    }

    public void setResourcesId(Long resourcesId) {
        this.sysRoleResources.setResourcesId(resourcesId);
    }

    public Date getCreateTime() {
        return this.sysRoleResources.getCreateTime();
    }

    public void setCreateTime(Date regTime) {
        this.sysRoleResources.setCreateTime(regTime);
    }

    public Date getUpdateTime() {
        return this.sysRoleResources.getUpdateTime();
    }

    public void setUpdateTime(Date updateTime) {
        this.sysRoleResources.setUpdateTime(updateTime);
    }
}
