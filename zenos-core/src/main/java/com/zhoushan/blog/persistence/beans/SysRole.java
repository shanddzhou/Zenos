package com.zhoushan.blog.persistence.beans;

import com.zhoushan.blog.framework.object.AbstractDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Transient;

/**
 * @author zhooo
 * @version V1.0
 * @Title: SysRole
 * @Package com.zhoushan.blog.persistence.beans
 * @Description: 系统角色
 * @date 2019/7/7 11:05
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysRole extends AbstractDO {
    private String name;
    private String description;
    private Boolean available;

    @Transient
    private Integer selected;

}
