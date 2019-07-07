package com.zhoushan.blog.business.service;

import com.github.pagehelper.PageInfo;
import com.zhoushan.blog.business.entity.Log;
import com.zhoushan.blog.business.enums.PlatformEnum;
import com.zhoushan.blog.business.vo.LogConditionVO;
import com.zhoushan.blog.framework.object.AbstractService;

public interface SysLogService extends AbstractService<Log,Integer> {
    /**
     * 分页查询
     * @param vo
     * @return
     */
    PageInfo<Log> findPageBreakByCondition(LogConditionVO vo);

    /**
     * 异步添加System日志
     * @param platform
     * @param businessName
     */
    void asyncAddSystemLog(PlatformEnum platform, String businessName);
}
