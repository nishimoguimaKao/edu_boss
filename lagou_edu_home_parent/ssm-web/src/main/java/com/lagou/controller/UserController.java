package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.*;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /*
        用户分页&多条件组合查询方法
     */

    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVo userVo) {

        PageInfo pageInfo = userService.findAllUserByPage(userVo);

        System.out.println("==================" + pageInfo + "===================");

        return new ResponseResult(true, 200, "分页多条件查询成功", pageInfo);

    }

    /*
        用户登陆
    */
    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) throws Exception {

        User login = userService.login(user);

        ResponseResult result = null;

        if (login != null) {
            // 保存access_token
            Map<String, Object> map = new HashMap<>();
            String access_token = UUID.randomUUID().toString();
            map.put("access_token", access_token);
            map.put("user_id", login.getId());

            // 将查询信息响应给前台
            HttpSession session = request.getSession();
            session.setAttribute("access_token", access_token);
            session.setAttribute("user_id", login.getId());

            // 将查询到的user也保存到session中
            map.put("user", login);

            result = new ResponseResult(true, 1, "响应成功！", map);
        } else {
            result = new ResponseResult(true, 200, "用户名密码错误！", null);
        }

        return result;
    }

    /*
        根据ID查询用户当前角色 分配角色，回显
     */
    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRoleById(Integer id) {
        List<Role> roleList = userService.findUserRelationRoleById(id);
        return new ResponseResult(true, 200, "分配角色回显成功！", roleList);
    }

    /*
        分配角色
     */
    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVo userVo) {
        userService.userContextRole(userVo);
        return new ResponseResult(true, 200, "分配角色成功！", null);
    }

    /*
        获取用户权限，进行菜单动态显示
     */
    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request) {

        // 1.获取请求头中的header
        String header_token = request.getHeader("Authorization");

        // 2.获取session中的token
        String session_token = (String) request.getSession().getAttribute("access_token");

        // 3.判断token是否一致
        if (header_token.equals(session_token)) {
            // 获取用户id
            Integer user_id = (Integer) request.getSession().getAttribute("user_id");

            // 调用service进行菜单信息查询
            ResponseResult userPermissions = userService.getUserPermissions(user_id);

            return userPermissions;
        } else {
            return new ResponseResult(false, 400, "获取菜单信息失败", null);
        }
    }

    /*
        更新用户状态信息
     */
    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(UserStatusVO userStatusVO) {

        if (userStatusVO.getStatus().equalsIgnoreCase("enable")) {
            userStatusVO.setStatus("DISABLE");
        } else if (userStatusVO.getStatus().equalsIgnoreCase("disable")) {
            userStatusVO.setStatus("ENABLE");
        }
        userService.updateUserStatus(userStatusVO);
        return new ResponseResult(true, 200, "修改用户状态成功！", userStatusVO.getStatus());
    }
}