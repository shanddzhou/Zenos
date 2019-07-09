package com.zhoushan.blog.persistence.mapper;

import com.zhoushan.blog.framework.object.BaseMapper;
import com.zhoushan.blog.persistence.beans.SysConfig;
import org.springframework.stereotype.Repository;

import java.util.Map;


/**
 * @author zhooo
 * @version V1.0
 * @Title: SysConfigMapper
 * @Package com.zhoushan.blog.persistence.mapper
 * @Description: TODO
 * @date 2019/7/8 10:52
 */
@Repository
public interface SysConfigMapper extends BaseMapper<SysConfig> {
    Map<String, Object> getSiteInfo();
}
