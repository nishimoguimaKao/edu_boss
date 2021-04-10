package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourseVo;
import com.lagou.domain.ResponseResult;
import com.lagou.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    public ResourceService resourceService;

    @RequestMapping("/findAllResource")
    public ResponseResult findAllResourceByPage(@RequestBody ResourseVo resourseVo){

        PageInfo<Resource> pageInfo = resourceService.findAllResourceByPage(resourseVo);

        return  new ResponseResult(true,200,"资源信息分页多条件查询成功",pageInfo);

    }


    /*
        添加&编辑
     */
    @RequestMapping("/saveOrUpdateResource")
    public ResponseResult saveOrUpdateResource(@RequestBody Resource resource){

        // 判断id是否为空
        if(resource.getId() ==null ){
            // 添加操作
            resourceService.saveResource(resource);
            return new ResponseResult(true,200,"添加资源成功！",null);
        }else {
            // 修改操作
            resourceService.updateResource(resource);
            return new ResponseResult(true,200,"修改资源成功！",null);
        }

    }

    /*
        删除操作
     */
    @RequestMapping("/deleteResource")
    public ResponseResult deleteResource(Integer id){

        resourceService.deleteResource(id);
        return new ResponseResult(true,200,"删除资源成功","");
    }



}
