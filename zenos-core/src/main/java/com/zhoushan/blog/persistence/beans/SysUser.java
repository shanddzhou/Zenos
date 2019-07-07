package com.zhoushan.blog.persistence.beans;

import com.zhoushan.blog.framework.object.AbstractDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author zhooo
 * @version V1.0
 * @Title: SysUser
 * @Package com.zhoushan.blog.persistence.beans
 * @Description: 用户
 * @date 2019/7/7 11:06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUser extends AbstractDO {
    private String username;
    private String password;
    private String nickname;
    private String mobile;
    private String email;
    private String qq;
    private Date birthday;
    private Integer gender;
    private String avatar;
    private String userType;
    private String blog;
    private String source;
    private String uuid;
    private Integer privacy;
    private Integer notification;
    private String regIp;
    private String lastLoginIp;
    private Date lastLoginTime;
    private Integer loginCount;
    private String remark;
    private Integer status;
}
