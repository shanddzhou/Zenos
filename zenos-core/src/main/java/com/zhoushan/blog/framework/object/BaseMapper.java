package com.zhoushan.blog.framework.object;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 公有Mapper，该接口不能被扫描，不然会出错
 * @param <T>
 */

public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
