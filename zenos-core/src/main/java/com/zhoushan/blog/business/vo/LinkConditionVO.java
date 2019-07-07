package com.zhoushan.blog.business.vo;

import com.zhoushan.blog.business.entity.Link;
import com.zhoushan.blog.framework.object.BaseConditionVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhooo
 * @version V1.0
 * @Title: LinkConditionVo
 * @Package com.zhoushan.blog.business.vo
 * @Description: TODO
 * @date 2019/7/7 16:58
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LinkConditionVO extends BaseConditionVO {
    private Link link;
    private Integer status;
    private Integer homePageDisplay;

    public LinkConditionVO() {
    }

    public LinkConditionVO(Integer status, Integer homePageDisplay) {
        this.status = status;
        this.homePageDisplay = homePageDisplay;
    }
}
