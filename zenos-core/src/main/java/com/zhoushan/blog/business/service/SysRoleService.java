package com.zhoushan.blog.business.service;

import com.github.pagehelper.PageInfo;
import com.zhoushan.blog.business.entity.Role;
import com.zhoushan.blog.business.vo.RoleConditionVO;
import com.zhoushan.blog.framework.object.AbstractService;

import java.util.List;
import java.util.Map;

/**
 * @author zhooo
 * @version V1.0
 * @Title: SysRoleService
 * @Package com.zhoushan.blog.business.service
 * @Description: TODO
 * @date 2019/7/7 17:55
 */
public interface SysRoleService extends AbstractService<Role,Long> {

    /**
     * 获取ztree使用的角色列表
     *
     * @param uid
     * @return
     */
    List<Map<String, Object>> queryRoleListWithSelected(Integer uid);

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    PageInfo<Role> findPageBreakByCondition(RoleConditionVO vo);

    /**
     * 获取用户的角色
     *
     * @param userId
     * @return
     */
    List<Role> listRolesByUserId(Long userId);
}
