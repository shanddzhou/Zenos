package com.zhoushan.blog.persistence.beans;

import com.zhoushan.blog.framework.object.AbstractDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhooo
 * @version V1.0
 * @Title: SysUserRole
 * @Package com.zhoushan.blog.persistence.beans
 * @Description: 用户和角色关联
 * @date 2019/7/7 11:06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUserRole extends AbstractDO {
    private Long userId;
    private Long roleId;
}

