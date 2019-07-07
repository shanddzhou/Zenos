package com.zhoushan.blog.business.service;

import com.zhoushan.blog.persistence.beans.SysConfig;

import java.util.Map;

/**
 * @author zhooo
 * @version V1.0
 * @Title: SysConfigService
 * @Package com.zhoushan.blog.business.service
 * @Description: TODO
 * @date 2019/7/7 15:09
 */
public interface SysConfigService {
    /**
     * 获取系统配置
     */
    Map<String, Object> getConfigs();

    /**
     * 添加、修改系统配置项
     * @param configs   所有配置项
     */
    void addConfig(Map<String, String> configs);

    /**
     * 添加、修改单个配置项
     * @param key
     * @param value
     */
    void addConfig(String key, String value);

    /**
     * 获取单个配置项
     * @param key
     */
    SysConfig getByKey(String key);

    /**
     * 获取站点详情
     * @return
     */
    Map<String, Object> getSiteInfo();

}
