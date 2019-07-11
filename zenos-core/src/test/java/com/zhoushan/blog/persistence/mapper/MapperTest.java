package com.zhoushan.blog.persistence.mapper;

import com.zhoushan.blog.business.vo.ResourcesConditionVO;
import com.zhoushan.blog.persistence.beans.SysResources;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author zhooo
 * @version V1.0
 * @Title: MapperTest
 * @Package com.zhoushan.blog.persistence.mapper
 * @Description: TODO
 * @date 7/11/2019 3:42 PM
 */
public class MapperTest {
    @Autowired
    private SysResourcesMapper sysResourcesMapper;
    @Test
    public void SysResoucesMapperTest() {
        ResourcesConditionVO vo = new ResourcesConditionVO();
        List<SysResources> pageBreakByCondition = sysResourcesMapper.findPageBreakByCondition(vo);
    }
}
