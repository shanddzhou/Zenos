package com.zhoushan.blog.framework.object;

import java.util.List;

/**
 * @author zhooo
 * @version V1.0
 * @Title: AbstractService
 * @Package com.zhoushan.blog.framework.object
 * @Description: Base Service 增、删、该、查
 * @date 2019/7/7 16:35
 */
public interface AbstractService<T,PK> {
    T insert(T entity);

    default void insertList(List<T> entities) {

    }

    boolean removeByPrimaryKey(PK primaryKey);

    boolean updateSelective(T entity);

    T getByPrimaryKey(PK primaryKey);

    default List<T> listAll() {
        return null;
    }
}
