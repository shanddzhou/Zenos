package com.zhoushan.blog.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhoushan.blog.business.entity.Resources;
import com.zhoushan.blog.business.service.SysResourcesService;
import com.zhoushan.blog.business.vo.ResourcesConditionVO;
import com.zhoushan.blog.persistence.beans.SysResources;
import com.zhoushan.blog.persistence.mapper.SysResourcesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author zhooo
 * @version V1.0
 * @Title: SysResourcesServiceImpl
 * @Package com.zhoushan.blog.business.service.impl
 * @Description: TODO
 * @date 2019/7/9 20:17
 */

@Service
public class SysResourcesServiceImpl implements SysResourcesService {
    @Autowired
    private SysResourcesMapper sysResourcesMapper;

    @Override
    public PageInfo<Resources> findPageBreakByCondition(ResourcesConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        List<SysResources> sysResourcesList = sysResourcesMapper.findPageBreakByCondition(vo);
        if (CollectionUtils.isEmpty(sysResourcesList)) {
            return null;
        }
        List<Resources> resourcesList = this.getResources(sysResourcesList);
        PageInfo pageInfo = new PageInfo<>(sysResourcesList);
        pageInfo.setList(resourcesList);
        return pageInfo;
    }

    @Override
    public List<Resources> listUserResources(Map<String, Object> map) {
        List<SysResources> sysResources = sysResourcesMapper.listUserResources(map);
        if (CollectionUtils.isEmpty(sysResources)) {
            return null;
        }
        return this.getResources(sysResources);
    }

    @Override
    public List<Map<String, Object>> queryResourcesListWithSelected(Long rid) {
        List<SysResources> sysResourcesList = sysResourcesMapper.queryResourcesListWithSelected(rid);
        if (CollectionUtils.isEmpty(sysResourcesList)) {
            return null;
        }
        List<Map<String, Object>> mapList = new ArrayList<>();
        Map<String, Object> map = null;
        for (SysResources sysResources : sysResourcesList) {
            map = new HashMap<String, Object>(3);
            map.put("id", sysResources.getId());
            map.put("pId", sysResources.getParentId());
            map.put("checked", sysResources.getChecked());
            map.put("name", sysResources.getName());
            mapList.add(map);
        }

        return mapList;
    }

    @Override
    public List<Resources> listUrlAndPermission() {
        List<SysResources> sysResourcesList = sysResourcesMapper.listUrlAndPermission();
        List<Resources> resourcesList = getResources(sysResourcesList);
        return resourcesList;
    }

    @Override
    public List<Resources> listAllAvailableMenu() {
        List<SysResources> sysResources = sysResourcesMapper.listAllAvailableMenu();
        List<Resources> resourcesList = getResources(sysResources);
        return resourcesList;
    }

    @Override
    public List<Resources> listByUserId(Long userId) {
        List<SysResources> sysResourcesList = sysResourcesMapper.listByUserId(userId);
        List<Resources> resourcesList = getResources(sysResourcesList);
        return resourcesList;
    }

    @Override
    public Resources insert(Resources entity) {
        Assert.notNull(entity, "Resources不能为空~");
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        sysResourcesMapper.insert(entity.getSysResources());
        return entity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByPrimaryKey(Long primaryKey) {
        Assert.notNull(primaryKey, "primaryKey不能为空~");
        return sysResourcesMapper.deleteByPrimaryKey(primaryKey) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateSelective(Resources entity) {
        Assert.notNull(entity, "Resources不能为空~");
        entity.setUpdateTime(new Date());
        return sysResourcesMapper.insertSelective(entity.getSysResources()) > 0;
    }

    @Override
    public Resources getByPrimaryKey(Long primaryKey) {
        Assert.notNull(primaryKey, "primaryKey不能为空~");
        SysResources sysResources = sysResourcesMapper.selectByPrimaryKey(primaryKey);
        return null == sysResources ? null : new Resources(sysResources);
    }

    private List<Resources> getResources(List<SysResources> sysResourcesList) {
        if (CollectionUtils.isEmpty(sysResourcesList)) {
            return null;
        }
        List<Resources> arrayList = new ArrayList<>();
        for (SysResources sysResources : sysResourcesList) {
            arrayList.add(new Resources(sysResources));
        }
        return arrayList;
    }
}
