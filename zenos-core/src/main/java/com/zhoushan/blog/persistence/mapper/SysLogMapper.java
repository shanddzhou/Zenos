package com.zhoushan.blog.persistence.mapper;

import com.zhoushan.blog.business.vo.LogConditionVO;
import com.zhoushan.blog.framework.object.BaseMapper;
import com.zhoushan.blog.persistence.beans.SysLog;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author zhooo
 * @version V1.0
 * @Title: SysLogMapper
 * @Package com.zhoushan.blog.persistence.mapper
 * @Description: TODO
 * @date 2019/7/8 22:59
 */
@Repository
public interface SysLogMapper extends BaseMapper<SysLog> {
    List<SysLog> findPageBreakByCondition(LogConditionVO vo);
}
