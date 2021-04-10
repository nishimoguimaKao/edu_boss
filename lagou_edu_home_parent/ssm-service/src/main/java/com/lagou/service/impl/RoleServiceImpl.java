package com.lagou.service.impl;

import com.lagou.dao.RoleMapper;
import com.lagou.domain.*;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;


    @Override
    public List<Role> findAllRole(Role role) {
        List<Role> allRole = roleMapper.findAllRole(role);
        return allRole;
    }

    @Override
    public List<Integer> findMenuByRoleId(Integer roleid) {
        List<Integer> menuByRoleId = roleMapper.findMenuByRoleId(roleid);

        return menuByRoleId;
    }

    @Override
    public void roleContextMenu(RoleMenuVo roleMenuVo) {

        //1. 清空中间表的关联关系
        roleMapper.deleteRoleContextMenu(roleMenuVo.getRoleId());

        //2. 为角色分配菜单

        for (Integer mid : roleMenuVo.getMenuIdList()) {

            Role_menu_relation role_menu_relation = new Role_menu_relation();
            role_menu_relation.setMenuId(mid);
            role_menu_relation.setRoleId(roleMenuVo.getRoleId());

            //封装数据
            Date date = new Date();
            role_menu_relation.setCreatedTime(date);
            role_menu_relation.setUpdatedTime(date);

            role_menu_relation.setCreatedBy("system");
            role_menu_relation.setUpdatedby("system");


            roleMapper.roleContextMenu(role_menu_relation);
        }

    }

    @Override
    public void deleteRole(Integer roleid) {

        // 调用根据roleid清空中间表关联关系
        roleMapper.deleteRoleContextMenu(roleid);

        roleMapper.deleteRole(roleid);
    }

    @Override
    public List<ResourceCategory> findResourceListByRoleId(Integer id) {

        // 先查找出资源分类的信息
        List<ResourceCategory> resourceCategoryList = roleMapper.findResourceCategoryListRoleId(id);

        // 在使用循环为每隔资源分类查找信息
        for (ResourceCategory resourceCategory : resourceCategoryList) {

            List<Resource> resourceList = roleMapper.findResourceListByRoleId(id,resourceCategory.getId());

            resourceCategory.setResourceList(resourceList);
        }

        return resourceCategoryList;
    }

    public void roleContextResource(RoleResourceVo roleResourceVo){

        // 调用根据roleid清空中间表关联关系
        roleMapper.deleteResourceById(roleResourceVo.getRoleId());

        Date date = new Date();
        // 再重新添加关系
        for (Integer resourceId : roleResourceVo.getResourceIdList()) {

            // 声明中间表对象
            RoleResourceRelation roleResourceRelation = new RoleResourceRelation();

            // 设置属性
            roleResourceRelation.setResourceId(resourceId);
            roleResourceRelation.setRoleId(roleResourceVo.getRoleId());
            roleResourceRelation.setCreatedTime(date);
            roleResourceRelation.setUpdatedTime(date);
            roleResourceRelation.setCreatedBy("system");
            roleResourceRelation.setUpdatedBy("system");

            roleMapper.saveResourceRelation(roleResourceRelation);
        }

    }

    @Override
    public void saveRole(Role role) {

        role.setCreatedTime(new Date());
        role.setUpdatedTime(new Date());
        role.setCreatedBy("system");
        role.setUpdatedBy("system");

        roleMapper.saveRole(role);

    }

    @Override
    public void updateRole(Role role) {

        role.setCreatedTime(new Date());
        role.setUpdatedTime(new Date());

        roleMapper.updateRole(role);
    }
}
