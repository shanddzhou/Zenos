package com.zhoushan.blog.business.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zhoushan.blog.business.enums.LogLevelEnum;
import com.zhoushan.blog.business.enums.LogTypeEnum;
import com.zhoushan.blog.persistence.beans.SysLog;

import java.util.Date;

/**
 * @author zhooo
 * @version V1.0
 * @Title: Log
 * @Package com.zhoushan.blog.business.entity
 * @Description: TODO
 * @date 2019/7/7 13:00
 */
public class Log {
    private SysLog sysLog;

    public Log(SysLog sysLog) {
        this.sysLog = sysLog;
    }

    public Log() {
        this.sysLog = new SysLog();
    }

    @JsonIgnore
    public SysLog getSysLog() {
        return this.sysLog;
    }

    public Long getId() {
        return this.sysLog.getId();
    }

    public void setId(Long id) {
        this.sysLog.setId(id);
    }

    public Long getUserId() {
        return this.sysLog.getUserId();
    }

    public void setUserId(Long userId) {
        this.sysLog.setUserId(userId);
    }

    public String getLogLevel() {
        return this.sysLog.getLogLevel();
    }

    public void setLogLevel(LogLevelEnum logLevel) {
        if (null == logLevel) {
            return;
        }
        this.sysLog.setLogLevel(logLevel.toString());
    }

    public void setLogLevel(String logLevel) {
        this.sysLog.setLogLevel(logLevel);
    }

    public String getIp() {
        return this.sysLog.getIp();
    }

    public void setIp(String ip) {
        this.sysLog.setIp(ip);
    }

    public String getContext() {
        return this.sysLog.getContext();
    }

    public void setContext(String context) {
        this.sysLog.setContext(context);
    }

    public String getParams() {
        return this.sysLog.getParams();
    }

    public void setParams(String params) {
        this.sysLog.setParams(params);
    }

    public String getType() {
        return this.sysLog.getType();
    }

    public void setType(String type) {
        this.sysLog.setType(type);
    }

    public void setType(LogTypeEnum type) {
        if (null == type) {
            return;
        }
        this.sysLog.setType(type.toString());
    }

    public String getUa() {
        return this.sysLog.getUa();
    }

    public void setUa(String ua) {
        this.sysLog.setUa(ua);
    }

    public String getOs() {
        return this.sysLog.getOs();
    }

    public void setOs(String os) {
        this.sysLog.setOs(os);
    }

    public String getBrower() {
        return this.sysLog.getBrower();
    }

    public void setBrower(String brower) {
        this.sysLog.setBrower(brower);
    }

    public String getRequestUrl() {
        return this.sysLog.getRequestUrl();
    }

    public void setRequestUrl(String requestUrl) {
        this.sysLog.setRequestUrl(requestUrl);
    }

    public String getReferer() {
        return this.sysLog.getReferer();
    }

    public void setReferer(String referer) {
        this.sysLog.setReferer(referer);
    }

    public Date getCreateTime() {
        return this.sysLog.getCreateTime();
    }

    public void setCreateTime(Date createTime) {
        this.sysLog.setCreateTime(createTime);
    }

    public Date getUpdateTime() {
        return this.sysLog.getUpdateTime();
    }

    public void setUpdateTime(Date updateTime) {
        this.sysLog.setUpdateTime(updateTime);
    }


}
