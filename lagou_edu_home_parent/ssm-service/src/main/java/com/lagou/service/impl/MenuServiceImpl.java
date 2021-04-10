package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.MenuMapper;
import com.lagou.domain.Menu;
import com.lagou.domain.MenuVO;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;


    @Override
    public List<Menu> findSubMenuListByPid(int pid) {
        List<Menu> subMenuListByPid = menuMapper.findSubMenuListByPid(pid);
        return subMenuListByPid;
    }

    @Override
    public PageInfo findAllMenu(MenuVO menuVO) {
        PageHelper.startPage(menuVO.getCurrentPage(),menuVO.getPageSize());
        List<Menu> allMenu = menuMapper.findAllMenu();
        PageInfo<Menu> menuPageInfo = new PageInfo<>(allMenu);
        return menuPageInfo;
    }

    @Override
    public Menu findMenuById(Integer id) {
        return menuMapper.findMenuById(id);
    }

    @Override
    public void saveMenu(Menu menu) {
        Date date = new Date();
        menu.setCreatedTime(date);
        menu.setUpdatedTime(date);
        menu.setCreatedBy("system");
        menu.setUpdatedBy("system");

        if( menu.getParentId() == -1){
            menu.setLevel(0); //父级菜单为 0
        }else{
            menu.setLevel(1); //子菜单为 1
        }

        menuMapper.saveMenu(menu);
    }

    @Override
    public void updateMenu(Menu menu) {
        Date date = new Date();
        menu.setUpdatedTime(date);
        menu.setCreatedBy("system");
        menu.setUpdatedBy("system");
        menuMapper.updateMenu(menu);
    }

    /*
        删除菜单
    */
    @Override
    public void deleteMenu(Integer id){
        menuMapper.deleteMenu(id);
    }
}
