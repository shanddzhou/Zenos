package com.zhoushan.blog.business.vo;

import com.zhoushan.blog.framework.object.BaseConditionVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhooo
 * @version V1.0
 * @Title: NoticeConditionVO
 * @Package com.zhoushan.blog.business.b
 * @Description: TODO
 * @date 2019/7/7 17:21
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class NoticeConditionVO extends BaseConditionVO {
    private String status;
}
