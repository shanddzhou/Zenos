package com.zhoushan.blog.business.vo;

import com.zhoushan.blog.business.entity.Role;
import com.zhoushan.blog.framework.object.BaseConditionVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhooo
 * @version V1.0
 * @Title: RoleConditionVO
 * @Package com.zhoushan.blog.business.vo
 * @Description: TODO
 * @date 2019/7/7 17:56
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RoleConditionVO extends BaseConditionVO {
    private Role role;
}
