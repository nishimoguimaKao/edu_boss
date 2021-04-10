package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.*;

import java.util.List;

public interface UserService {

    /*
        用户分页&多条件查询
     */
    public PageInfo findAllUserByPage(UserVo userVo);

    /*
        用户登陆
    */
    public User login(User user) throws Exception;

    /*
     * 根据ID查询用户当前角色
     */
    public List<Role> findUserRelationRoleById(int id);

    /*
        用户关联角色
     */
    public void userContextRole(UserVo userVo);

    /*
        获取用户权限，进行菜单动态显示
     */
    public ResponseResult getUserPermissions(Integer userId);

    /*
        更新用户状态信息
    */
    public void updateUserStatus(UserStatusVO userStatusVO);
}
