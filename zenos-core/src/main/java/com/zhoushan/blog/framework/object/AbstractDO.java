package com.zhoushan.blog.framework.object;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author zhooo
 * @version V1.0
 * @Title: AbstractDO
 * @Package com.zhoushan.blog.framework.object
 * @Description: TODO
 * @date 2019/7/7 10:55
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AbstractDO implements Serializable {
    private static final long serialVersionUID = 1820832791846151148L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date createTime;
    private Date updateTime;
}
