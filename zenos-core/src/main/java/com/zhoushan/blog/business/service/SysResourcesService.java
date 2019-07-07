package com.zhoushan.blog.business.service;

import com.github.pagehelper.PageInfo;
import com.zhoushan.blog.business.entity.Resources;
import com.zhoushan.blog.business.vo.ResourcesConditionVO;
import com.zhoushan.blog.framework.object.AbstractService;

import java.util.List;
import java.util.Map;

/**
 * @author zhooo
 * @version V1.0
 * @Title: SysResourcesService
 * @Package com.zhoushan.blog.business.service
 * @Description: TODO
 * @date 2019/7/7 17:39
 */
public interface SysResourcesService extends AbstractService<Resources, Long> {
    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    PageInfo<Resources> findPageBreakByCondition(ResourcesConditionVO vo);

    /**
     * 获取用户的资源
     *
     * @param map
     * @return
     */
    List<Resources> listUserResources(Map<String, Object> map);

    /**
     * 获取ztree使用的资源列表
     *
     * @param rid
     * @return
     */
    List<Map<String, Object>> queryResourcesListWithSelected(Long rid);

    /**
     * 获取资源的url和permission
     *
     * @return
     */
    List<Resources> listUrlAndPermission();

    /**
     * 获取所有可用的菜单资源
     *
     * @return
     */
    List<Resources> listAllAvailableMenu();

    /**
     * 获取用户关联的所有资源
     *
     * @param userId
     * @return
     */
    List<Resources> listByUserId(Long userId);
}
