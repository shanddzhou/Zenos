package com.zhoushan.blog.business.vo;

import com.zhoushan.blog.business.entity.Resources;
import com.zhoushan.blog.framework.object.BaseConditionVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhooo
 * @version V1.0
 * @Title: ResourcesConditionVO
 * @Package com.zhoushan.blog.business.vo
 * @Description: TODO
 * @date 2019/7/7 17:51
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ResourcesConditionVO extends BaseConditionVO {
    private Resources resources;
}
