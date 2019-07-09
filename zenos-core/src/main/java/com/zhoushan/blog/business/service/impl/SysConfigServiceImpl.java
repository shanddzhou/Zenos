package com.zhoushan.blog.business.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.zhoushan.blog.business.annotation.RedisCache;
import com.zhoushan.blog.business.enums.ConfigKeyEnum;
import com.zhoushan.blog.business.service.SysConfigService;
import com.zhoushan.blog.persistence.beans.SysConfig;
import com.zhoushan.blog.persistence.mapper.SysConfigMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhooo
 * @version V1.0
 * @Title: SysConfigServiceImpl
 * @Package com.zhoushan.blog.business.service.impl
 * @Description: TODO
 * @date 2019/7/8 10:39
 */
@Slf4j
@Service
public class SysConfigServiceImpl implements SysConfigService {
    @Autowired
    private SysConfigMapper sysConfigMapper;

    /**
     * 获取系统配置
     *
     * @return
     */
    @Override
    @RedisCache(enable = false)
    public Map<String, Object> getConfigs() {
        List<SysConfig> sysConfigs = sysConfigMapper.selectAll();
        if (CollectionUtils.isEmpty(sysConfigs)) {
            return null;
        }
        String updateTimeKey = ConfigKeyEnum.UPDATE_TIME.getKey();
        HashMap<String, Object> res = new HashMap<>();
        res.put(updateTimeKey, DateUtil.parse("2019-07-08 00:00:00", "yyyy-MM-dd HH:mm:ss"));
        sysConfigs.forEach((sysConfig) -> {
            res.put(String.valueOf(sysConfig.getConfigKey()), sysConfig.getConfigValue());
            if (sysConfig.getUpdateTime().after((Date) res.get("updateTimeKey"))) {
                res.put(updateTimeKey, sysConfig.getUpdateTime());
            }
        });
        String storageType = (String) res.get(ConfigKeyEnum.STORAGE_TYPE.getKey());
        if ("local".equalsIgnoreCase(storageType)) {
            res.put("fileStoragePath", res.get(ConfigKeyEnum.LOCAL_FILE_URL.getKey()));
        } else if ("qiniu".equalsIgnoreCase(storageType)) {
            res.put("fileStoragePath", res.get(ConfigKeyEnum.QINIU_BASE_PATH.getKey()));
        } else if ("aliyun".equalsIgnoreCase(storageType)) {
            res.put("fileStoragePath", res.get(ConfigKeyEnum.ALIYUN_FILE_URL.getKey()));
        }
        return res;
    }

    @Override
    @RedisCache(flush = true,enable = false)
    public void addConfig(Map<String, String> configs) {
        if (!CollectionUtils.isEmpty(configs)) {
            configs.forEach(this::addConfig);
        }
    }

    @Override
    @RedisCache(flush = true,enable = false)
    public void addConfig(String key, String value) {
        if (!StringUtils.isEmpty(key)) {
            SysConfig sysConfig = null;
            if (null == (sysConfig = this.getByKey(key))) {
                sysConfig = new SysConfig();
                sysConfig.setConfigKey(key);
                sysConfig.setConfigValue(value);
                sysConfig.setCreateTime(new Date());
                sysConfig.setUpdateTime(new Date());
            } else {
                sysConfig.setConfigKey(key);
                sysConfig.setConfigValue(value);
                sysConfig.setUpdateTime(new Date());
                this.sysConfigMapper.updateByPrimaryKeySelective(sysConfig);
            }
        }
    }

    @Override
    @RedisCache(enable = false)
    public SysConfig getByKey(String key) {
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        SysConfig sysConfig = new SysConfig();
        sysConfig.setConfigKey(key);
        return this.sysConfigMapper.selectOne(sysConfig);
    }

    @Override
    public Map<String, Object> getSiteInfo() {
        Map<String, Object> siteInfo = sysConfigMapper.getSiteInfo();
        if (!CollectionUtils.isEmpty(siteInfo)) {
            Date installDate = null;
            SysConfig config = this.getByKey(ConfigKeyEnum.INSTALLDATE.getKey());
            if (null == config || StringUtils.isEmpty(config.getConfigValue())) {
                installDate = Date.from(LocalDate.of(2019, 7, 8).atStartOfDay(ZoneId.systemDefault()).toInstant());
            } else {
                installDate = DateUtil.parse(config.getConfigValue(), DatePattern.NORM_DATETIME_PATTERN);
            }
            long between = 1;
            if (!installDate.after(new Date())) {
                between = DateUtil.between(installDate, new Date(), DateUnit.DAY);
            }
            siteInfo.put("installdate", between < 1 ? 1 : between);
        }
        return siteInfo;
    }
}
