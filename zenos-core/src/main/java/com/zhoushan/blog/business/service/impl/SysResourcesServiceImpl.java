package com.zhoushan.blog.business.service.impl;

import com.github.pagehelper.PageInfo;
import com.zhoushan.blog.business.entity.Resources;
import com.zhoushan.blog.business.service.SysResourcesService;
import com.zhoushan.blog.business.vo.ResourcesConditionVO;
import com.zhoushan.blog.persistence.mapper.SysResourcesMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @author zhooo
 * @version V1.0
 * @Title: SysResourcesServiceImpl
 * @Package com.zhoushan.blog.business.service.impl
 * @Description: TODO
 * @date 2019/7/9 20:17
 */
public class SysResourcesServiceImpl implements SysResourcesService {
    @Autowired
    private SysResourcesMapper sysResourcesMapper;
    @Override
    public PageInfo<Resources> findPageBreakByCondition(ResourcesConditionVO vo) {
        return null;
    }

    @Override
    public List<Resources> listUserResources(Map<String, Object> map) {
        return null;
    }

    @Override
    public List<Map<String, Object>> queryResourcesListWithSelected(Long rid) {
        return null;
    }

    @Override
    public List<Resources> listUrlAndPermission() {
        return null;
    }

    @Override
    public List<Resources> listAllAvailableMenu() {
        return null;
    }

    @Override
    public List<Resources> listByUserId(Long userId) {
        return null;
    }

    @Override
    public Resources insert(Resources entity) {
        return null;
    }

    @Override
    public boolean removeByPrimaryKey(Long primaryKey) {
        return false;
    }

    @Override
    public boolean updateSelective(Resources entity) {
        return false;
    }

    @Override
    public Resources getByPrimaryKey(Long primaryKey) {
        return null;
    }
}
