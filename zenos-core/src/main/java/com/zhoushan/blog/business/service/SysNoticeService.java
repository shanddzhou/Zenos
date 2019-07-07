package com.zhoushan.blog.business.service;

import com.github.pagehelper.PageInfo;
import com.zhoushan.blog.business.dto.SysNoticeDTO;
import com.zhoushan.blog.business.entity.Notice;
import com.zhoushan.blog.business.vo.NoticeConditionVO;
import com.zhoushan.blog.framework.object.AbstractService;

import java.util.List;

/**
 * @author zhooo
 * @version V1.0
 * @Title: SysNoticeService
 * @Package com.zhoushan.blog.business.service
 * @Description: 系统消息
 * @date 2019/7/7 17:19
 */
public interface SysNoticeService extends AbstractService<Notice, Long> {
    /**
     * 分页查询
     * @param vo
     * @return
     */
    PageInfo<Notice> findPageBreakByCondition(NoticeConditionVO vo);

    /**
     * 获取已发布的通知
     * @return
     */
    List<SysNoticeDTO> listRelease();
}
