package com.zhoushan.blog.business.service;

import com.github.pagehelper.PageInfo;
import com.zhoushan.blog.business.entity.Link;
import com.zhoushan.blog.business.vo.LinkConditionVO;
import com.zhoushan.blog.framework.object.AbstractService;

import java.util.List;
import java.util.Map;

public interface SysLinkService extends AbstractService<Link, Long> {
    /**
     * 通过Url获取Link
     * @param Url
     * @return
     */
    Link getOneByUrl(String Url);

    /**
     *  通过条件查询分页
     * @param vo
     * @return
     */
    PageInfo<Link> findPageBreakByCondition(LinkConditionVO vo);

    /**
     * 查询能够在首页显示的友情链接
     * @return
     */
    List<Link> listOfIndex();

    /**
     * 查询能够在页内显示的友情链接
     * @return
     */
    List<Link> listOfInside();

    /**
     * 查询被禁用的友情链接
     * @return
     */
    List<Link> listOfDisable();

    /**
     * 分类获取所有的友情链接
     * @return{index:,inside:,disable:}
     */
    Map<String, List<Link>> listAllByGroup();

}
