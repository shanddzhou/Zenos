package com.zhoushan.blog.persistence.mapper;

import com.zhoushan.blog.business.vo.LinkConditionVO;
import com.zhoushan.blog.framework.object.BaseMapper;
import com.zhoushan.blog.persistence.beans.SysLink;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhooo
 * @version V1.0
 * @Title: SysLinkMapper
 * @Package com.zhoushan.blog.persistence.mapper
 * @Description: TODO
 * @date 2019/7/8 21:29
 */
@Repository
public interface SysLinkMapper extends BaseMapper<SysLink> {
    /**
     * 分页查询
     */
    List<SysLink> findPageBreakByCondition(LinkConditionVO vo);
}
