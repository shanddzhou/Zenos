package com.zhoushan.blog.business.service.impl;

import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhoushan.blog.business.entity.Log;
import com.zhoushan.blog.business.entity.User;
import com.zhoushan.blog.business.enums.LogLevelEnum;
import com.zhoushan.blog.business.enums.LogTypeEnum;
import com.zhoushan.blog.business.enums.PlatformEnum;
import com.zhoushan.blog.business.service.SysLogService;
import com.zhoushan.blog.business.vo.LogConditionVO;
import com.zhoushan.blog.persistence.beans.SysLog;
import com.zhoushan.blog.persistence.mapper.SysLogMapper;
import com.zhoushan.blog.utils.RequestUtill;
import com.zhoushan.blog.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zhooo
 * @version V1.0
 * @Title: SysLogServiceImpl
 * @Package com.zhoushan.blog.business.service.impl
 * @Description: TODO
 * @date 2019/7/8 22:50
 */
@Service
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public PageInfo<Log> findPageBreakByCondition(LogConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        List<SysLog> sysLogList = sysLogMapper.findPageBreakByCondition(vo);
        if (CollectionUtils.isEmpty(sysLogList)) {
            return null;
        }
        ArrayList<Log> arrayList = new ArrayList<>();
        for (SysLog sysLog : sysLogList) {
            arrayList.add(new Log(sysLog));
        }
        PageInfo pageInfo = new PageInfo<>(sysLogList);
        pageInfo.setList(arrayList);
        return pageInfo;
    }

    @Override
    @Async
    public void asyncAddSystemLog(PlatformEnum platform, String businessName) {
        String ua = RequestUtill.getUa();
        Log log = new Log();
        log.setUa(ua);
        log.setLogLevel(LogLevelEnum.INFO);
        log.setIp(RequestUtill.getIp());
        log.setType(PlatformEnum.WEB.equals(platform) ? LogTypeEnum.VISIT : LogTypeEnum.SYSTEM);
        log.setReferer(RequestUtill.getReferer());
        log.setRequestUrl(RequestUtill.getRequestUrl());
        log.setParams(JSONObject.toJSONString(RequestUtill.getParametersMap()));
        User user = SessionUtil.getUser();
        if (null != user) {
            log.setUserId(user.getId());
            log.setContext(String.format("用户: [%s] | 操作: %s", user.getUsername(), businessName));
        } else {
            log.setContext(String.format("访客: [%s] | 操作: %s", log.getId(), businessName));
        }
        try {
            UserAgent userAgent = UserAgentUtil.parse(ua);
            log.setBrower(userAgent.getBrowser().toString());
            log.setOs(userAgent.getOs().toString());
            this.insert(log);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Log insert(Log entity) {
        Assert.notNull(entity, "Log不能为空！");
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        sysLogMapper.insertSelective(entity.getSysLog());
        return entity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByPrimaryKey(Integer primaryKey) {
        return sysLogMapper.deleteByPrimaryKey(primaryKey) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateSelective(Log entity) {
        Assert.notNull(entity, "Log不能为空！");
        entity.setUpdateTime(new Date());
        return sysLogMapper.updateByPrimaryKeySelective(entity.getSysLog()) > 0;
    }

    @Override
    public Log getByPrimaryKey(Integer primaryKey) {
        Assert.notNull(primaryKey, "primaryKey不能为空！");
        SysLog sysLog = sysLogMapper.selectByPrimaryKey(primaryKey);
        return null == sysLog ? null : new Log(sysLog);
    }
}
