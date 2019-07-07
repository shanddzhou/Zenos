package com.zhoushan.blog.persistence.beans;

import com.zhoushan.blog.framework.object.AbstractDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Transient;
import java.util.List;

/**
 * @author zhooo
 * @version V1.0
 * @Title: SysResources
 * @Package com.zhoushan.blog.persistence.beans
 * @Description: 用户权限资源
 * @date 2019/7/7 11:04
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysResources extends AbstractDO {
    private String name;
    private String type;
    private String url;
    private String permission;
    private Long parentId;
    private Integer sort;
    private Boolean external;
    private Boolean available;
    private String icon;

    @Transient
    private String checked;
    @Transient
    private SysResources parent;
    @Transient
    private List<SysResources> nodes;

}
