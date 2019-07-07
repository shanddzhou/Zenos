package com.zhoushan.blog.business.vo;

import com.zhoushan.blog.framework.object.BaseConditionVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhooo
 * @version V1.0
 * @Title: LogConditionVo
 * @Package com.zhoushan.blog.business.vo
 * @Description: TODO
 * @date 2019/7/7 17:14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LogConditionVO extends BaseConditionVO {
    private Long userId;
    private String logLevel;
    private String type;
}
