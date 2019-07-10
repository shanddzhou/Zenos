package com.zhoushan.blog.persistence.mapper;

import com.zhoushan.blog.business.vo.ResourcesConditionVO;
import com.zhoushan.blog.framework.object.BaseMapper;
import com.zhoushan.blog.persistence.beans.SysResources;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author zhooo
 * @version V1.0
 * @Title: SysResourcesMapper
 * @Package com.zhoushan.blog.persistence.mapper
 * @Description: TODO
 * @date 2019/7/9 20:18
 */
@Repository
public interface SysResourcesMapper extends BaseMapper<SysResources> {

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    List<SysResources> findPageBreakByCondition(ResourcesConditionVO vo);

    List<SysResources> listUserResources(Map<String, Object> map);

    List<SysResources> queryResourcesListWithSelected(Long rid);

    List<SysResources> listUrlAndPermission();

    List<SysResources> listAllAvailableMenu();

    List<SysResources> listByUserId(Long userId);

}
