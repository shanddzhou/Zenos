package com.zhoushan.blog.framework.object;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author zhooo
 * @version V1.0
 * @Title: BaseConditionVo
 * @Package com.zhoushan.blog.framework.object
 * @Description: TODO
 * @date 2019/7/7 16:59
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseConditionVO {
    private final static int DEFAULT_PAGE_SIZE = 10;
    private int pageNumber = 1;
    private int pageSize = 0;
    private int pageStart = 0;
    private String orderField;
    private String orderDirection;
    private String keywords;
    @DateTimeFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    private Date endDate;

    public int getPageSize() {
        return pageSize > 0 ? pageSize : DEFAULT_PAGE_SIZE;
    }

    public int getPageStart() {
        return pageNumber > 0 ? (pageNumber - 1) * getPageSize() : 0;
    }
}
