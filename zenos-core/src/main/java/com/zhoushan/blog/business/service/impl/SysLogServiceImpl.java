package com.zhoushan.blog.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhoushan.blog.business.entity.Log;
import com.zhoushan.blog.business.enums.PlatformEnum;
import com.zhoushan.blog.business.service.SysLogService;
import com.zhoushan.blog.business.vo.LogConditionVO;
import com.zhoushan.blog.persistence.beans.SysLog;
import com.zhoushan.blog.persistence.mapper.SysLogMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author zhooo
 * @version V1.0
 * @Title: SysLogServiceImpl
 * @Package com.zhoushan.blog.business.service.impl
 * @Description: TODO
 * @date 2019/7/8 22:50
 */
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    private SysLogMapper sysLogMapper;
    @Override
    public PageInfo<Log> findPageBreakByCondition(LogConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        Map<String, Object> pageBreakByCondition = sysLogMapper.findPageBreakByCondition(vo);
    }

    @Override
    public void asyncAddSystemLog(PlatformEnum platform, String businessName) {

    }

    @Override
    public Log insert(Log entity) {
        return null;
    }

    @Override
    public boolean removeByPrimaryKey(Integer primaryKey) {
        return false;
    }

    @Override
    public boolean updateSelective(Log entity) {
        return false;
    }

    @Override
    public Log getByPrimaryKey(Integer primaryKey) {
        return null;
    }
}
