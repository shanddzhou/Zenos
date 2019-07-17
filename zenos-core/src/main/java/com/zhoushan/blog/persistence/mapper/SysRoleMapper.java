package com.zhoushan.blog.persistence.mapper;

import com.zhoushan.blog.business.vo.RoleConditionVO;
import com.zhoushan.blog.framework.object.BaseMapper;
import com.zhoushan.blog.persistence.beans.SysRole;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhooo
 * @version V1.0
 * @Title: SysRoleMapper
 * @Package com.zhoushan.blog.persistence.mapper
 * @Description: TODO
 * @date 7/15/2019 1:20 PM
 */
@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<SysRole> findPageBreakByCondition(RoleConditionVO vo);

    List<SysRole> queryRoleListWithSelected(Integer userId);

    List<SysRole> listRolesByUserId(Long userId);
}
