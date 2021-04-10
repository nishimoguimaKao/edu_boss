package com.lagou.dao;

import com.lagou.domain.Resource;
import com.lagou.domain.ResourseVo;

import java.util.List;

public interface ResourceMapper {


    /*
        资源分页&多条件查询
     */

    public List<Resource> findAllResourceByPage(ResourseVo resourseVo);

    /*
        保存资源
     */
    void saveResource(Resource resource);

    /*
        修改资源
     */
    void updateResource(Resource resource);

    /*
        删除资源
     */
    void deleteResource(Integer id);

}
