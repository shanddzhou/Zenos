package com.zhoushan.blog.business.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zhoushan.blog.persistence.beans.SysRole;

import java.util.Date;

/**
 * @author zhooo
 * @version V1.0
 * @Title: Role
 * @Package com.zhoushan.blog.business.entity
 * @Description: TODO
 * @date 2019/7/7 13:00
 */
public class Role {
    private SysRole sysRole;

    public Role() {
        this.sysRole = new SysRole();
    }

    public Role(SysRole sysRole) {
        this.sysRole = sysRole;
    }

    @JsonIgnore
    public SysRole getSysRole() {
        return this.sysRole;
    }

    public Long getId() {
        return this.sysRole.getId();
    }

    public void setId(Long id) {
        this.sysRole.setId(id);
    }

    public String getName() {
        return this.sysRole.getName();
    }

    public void setName(String name) {
        this.sysRole.setName(name);
    }


    public String getDescription() {
        return this.sysRole.getDescription();
    }

    public void setDescription(String description) {
        this.sysRole.setDescription(description);
    }

    public boolean isAvailable() {
        Boolean value = this.sysRole.getAvailable();
        return value != null ? value : false;
    }

    public void setAvailable(boolean available) {
        this.sysRole.setAvailable(available);
    }

    public Date getCreateTime() {
        return this.sysRole.getCreateTime();
    }

    public void setCreateTime(Date regTime) {
        this.sysRole.setCreateTime(regTime);
    }

    public Date getUpdateTime() {
        return this.sysRole.getUpdateTime();
    }

    public void setUpdateTime(Date updateTime) {
        this.sysRole.setUpdateTime(updateTime);
    }
}
