package com.zhoushan.blog.business.service.impl;

import com.github.pagehelper.PageInfo;
import com.zhoushan.blog.business.entity.Role;
import com.zhoushan.blog.business.service.SysRoleService;
import com.zhoushan.blog.business.vo.RoleConditionVO;
import com.zhoushan.blog.persistence.mapper.SysRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author zhooo
 * @version V1.0
 * @Title: SysRoleServiceImpl
 * @Package com.zhoushan.blog.business.service.impl
 * @Description: TODO
 * @date 7/15/2019 1:17 PM
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<Map<String, Object>> queryRoleListWithSelected(Integer uid) {
        return null;
    }

    @Override
    public PageInfo<Role> findPageBreakByCondition(RoleConditionVO vo) {
        return null;
    }

    @Override
    public List<Role> listRolesByUserId(Long userId) {
        return null;
    }

    @Override
    public Role insert(Role entity) {
        return null;
    }

    @Override
    public boolean removeByPrimaryKey(Long primaryKey) {
        return false;
    }

    @Override
    public boolean updateSelective(Role entity) {
        return false;
    }

    @Override
    public Role getByPrimaryKey(Long primaryKey) {
        return null;
    }
}
