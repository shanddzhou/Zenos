package com.zhoushan.blog.persistence.beans;

import com.zhoushan.blog.framework.object.AbstractDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhooo
 * @version V1.0
 * @Title: SysRoleResources
 * @Package com.zhoushan.blog.persistence.beans
 * @Description: 资源与角色关联
 * @date 2019/7/7 11:05
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysRoleResources extends AbstractDO {
    private Long roleId;
    private Long resourcesId;

}
