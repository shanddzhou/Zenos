package com.zhoushan.blog.business.vo;

import com.zhoushan.blog.business.entity.User;
import com.zhoushan.blog.framework.object.BaseConditionVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhooo
 * @version V1.0
 * @Title: UserConditionVO
 * @Package com.zhoushan.blog.business.b
 * @Description: TODO
 * @date 2019/7/7 18:11
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserConditionVO extends BaseConditionVO {
    private User user;
}

