package com.zhoushan.blog.persistence.beans;

import com.zhoushan.blog.framework.object.AbstractDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhooo
 * @version V1.0
 * @Title: SysConfig
 * @Package com.zhoushan.blog.persistence.beans
 * @Description: 系统配置信息
 * @date 2019/7/7 11:00
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysConfig extends AbstractDO {
    private String configKey;
    private String configValue;
}