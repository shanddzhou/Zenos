package com.zhoushan.blog.persistence.mapper;

import com.zhoushan.blog.business.vo.UserConditionVO;
import com.zhoushan.blog.framework.object.BaseMapper;
import com.zhoushan.blog.persistence.beans.SysUser;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhooo
 * @version V1.0
 * @Title: SysUserMapper
 * @Package com.zhoushan.blog.persistence.mapper
 * @Description: TODO
 * @date 2019/7/9 16:25
 */
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {
    List<SysUser> findPageBreakByCondition(UserConditionVO vo);

    List<SysUser> listByRoleId(Long roleId);
}
