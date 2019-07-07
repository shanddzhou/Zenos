package com.zhoushan.blog.persistence.beans;

import com.zhoushan.blog.framework.object.AbstractDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhooo
 * @version V1.0
 * @Title: SysLog
 * @Package com.zhoushan.blog.persistence.beans
 * @Description: 用户操作日志记录
 * @date 2019/7/7 11:01
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysLog extends AbstractDO {
    private Long userId;
    private String type;
    private String logLevel;
    private String context;
    private String params;
    private String ip;
    private String ua;
    private String os;
    private String brower;
    private String requestUrl;
    private String referer;
}
