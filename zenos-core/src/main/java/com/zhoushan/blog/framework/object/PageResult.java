package com.zhoushan.blog.framework.object;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author zhooo
 * @version V1.0
 * @Title: PageResult
 * @Package com.zhoushan.blog.framework.object
 * @Description: TODO
 * @date 7/12/2019 11:32 PM
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PageResult {
    private Long total;
    private List rows;

    public PageResult(Long total, List rows) {
        this.total = total;
        this.rows = rows;
    }

    public PageResult() {

    }
}
