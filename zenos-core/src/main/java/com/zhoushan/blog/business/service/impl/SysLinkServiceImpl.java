package com.zhoushan.blog.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhoushan.blog.business.annotation.RedisCache;
import com.zhoushan.blog.business.entity.Link;
import com.zhoushan.blog.business.service.SysLinkService;
import com.zhoushan.blog.business.vo.LinkConditionVO;
import com.zhoushan.blog.persistence.beans.SysLink;
import com.zhoushan.blog.persistence.mapper.SysLinkMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author zhooo
 * @version V1.0
 * @Title: SysLinkServiceImpl
 * @Package com.zhoushan.blog.business.service.impl
 * @Description: TODO
 * @date 2019/7/8 21:20
 */
@Slf4j
@Service
public class SysLinkServiceImpl implements SysLinkService {
    @Autowired
    private SysLinkMapper sysLinkMapper;

    @Override
    public Link getOneByUrl(String Url) {
        SysLink sysLink = new SysLink();
        sysLink.setUrl(Url);
        sysLink = sysLinkMapper.selectOne(sysLink);
        return null == sysLink ? null : new Link(sysLink);
    }

    @Override
    public PageInfo<Link> findPageBreakByCondition(LinkConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        List<SysLink> sysLinkList = sysLinkMapper.findPageBreakByCondition(vo);
        if (CollectionUtils.isEmpty(sysLinkList)) {
            return null;
        }
        ArrayList<Object> arrayList = new ArrayList<>();
        for (SysLink sysLink : sysLinkList) {
            arrayList.add(new Link(sysLink));
        }
        PageInfo pageInfo = new PageInfo<SysLink>(sysLinkList);
        pageInfo.setList(arrayList);
        return pageInfo;
    }

    @Override
    @RedisCache
    public List<Link> listOfIndex() {
        LinkConditionVO vo = new LinkConditionVO(1, 1);
        vo.setPageSize(100);
        PageInfo<Link> pageInfo = this.findPageBreakByCondition(vo);
        return null == pageInfo ? null : pageInfo.getList();
    }

    @Override
    @RedisCache
    public List<Link> listOfInside() {
        LinkConditionVO vo = new LinkConditionVO(1, 0);
        vo.setPageSize(100);
        PageInfo<Link> pageInfo = this.findPageBreakByCondition(vo);
        return null == pageInfo ? null : pageInfo.getList();
    }

    @Override
    @RedisCache
    public List<Link> listOfDisable() {
        LinkConditionVO vo = new LinkConditionVO(0, null);
        vo.setPageSize(100);
        PageInfo<Link> pageInfo = this.findPageBreakByCondition(vo);
        return null == pageInfo ? null : pageInfo.getList();
    }

    @Override
    @RedisCache
    public Map<String, List<Link>> listAllByGroup() {
        List<Link> listOfIndex = this.listOfIndex();
        List<Link> listOfInside = this.listOfInside();
        List<Link> listOfDisable = this.listOfDisable();
        HashMap<String, List<Link>> resultMap = new HashMap<>();
        resultMap.put("indexList", listOfIndex);
        resultMap.put("insideList", listOfInside);
        resultMap.put("disableList", listOfDisable);
        return resultMap;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @RedisCache(flush = true)
    public Link insert(Link entity) {
        Assert.notNull(entity, "Link不可为空！");
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        entity.setStatus(entity.isStatus());
        entity.setHomePageDisplay(entity.isHomePageDisplay());
        sysLinkMapper.insertSelective(entity.getSysLink());
        return entity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @RedisCache(flush = true)
    public boolean removeByPrimaryKey(Long primaryKey) {
        Assert.notNull(primaryKey, "primaryKey不能为空~");
        return sysLinkMapper.deleteByPrimaryKey(primaryKey) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @RedisCache(flush = true)
    public boolean updateSelective(Link entity) {
        Assert.notNull(entity, "Link不能为空！");
        entity.setUpdateTime(new Date());
        entity.setStatus(entity.isStatus());
        entity.setHomePageDisplay(entity.isHomePageDisplay());

        return sysLinkMapper.updateByPrimaryKeySelective(entity.getSysLink()) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @RedisCache
    public Link getByPrimaryKey(Long primaryKey) {
        Assert.notNull(primaryKey, "primaryKey不能为空！");
        SysLink sysLink = sysLinkMapper.selectByPrimaryKey(primaryKey);
        return null == sysLink ? null : new Link(sysLink);
    }
}
