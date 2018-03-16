package com.magicears.bpm.service;

import com.magicears.bpm.dao.MenuDao;
import com.magicears.bpm.dao.RoleMenuDao;
import com.magicears.bpm.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/3/15.
 */
@Transactional
@Service
public class MenuService {

    private final MenuDao menuDao;

    private final RoleMenuDao roleMenuDao;



    @Autowired
    public MenuService(MenuDao menuDao, RoleMenuDao roleMenuDao) {
        this.menuDao = menuDao;
        this.roleMenuDao = roleMenuDao;
    }



    /**
     * 获取全部菜单树
     *
     * @return
     */
    public List<Menu> findAll() {
        List<Menu> parents = menuDao.findParents();
        for (Menu parent : parents) {
            List<Menu> children = findChildren(parent.getId());
            parent.setChildren(children);
        }
        return parents;
    }

    public Menu find(Long id) {
        Menu menu = menuDao.find(id);
        return menu;
    }

    /**
     * 获取子菜单
     *
     * @param parentId
     * @return
     */
    public List<Menu> findChildren(Long parentId) {

        List<Menu> menus = menuDao.findByParentId(parentId);
        for (Menu menu : menus) {
            List<Menu> children = findChildren(menu.getId());
            menu.setChildren(children);
        }

        return menus;
    }


    /**
     * 通过角色获取权限
     *
     * @param roleId
     * @return
     */
    public List<Menu> findAllByRoleId(Long roleId) {
        List<Menu> menus = new ArrayList<>();
        List<Menu> parents = menuDao.findParentsByRoleId(roleId);
        menus.addAll(parents);
        for (Menu parent : parents) {
            List<Menu> children = findChildrenByRole(parent.getId(), roleId);
            for (Menu child : children) {
                if (child.getChildren() != null && !child.getChildren().isEmpty()) {
                    menus.addAll(child.getChildren());
                }
            }
            menus.addAll(children);

        }
        return menus;
    }


    /**
     * 获取子菜单
     *
     * @param parentId
     * @param roleId
     * @return
     */
    public List<Menu> findChildrenByRole(Long parentId, Long roleId) {
        List<Menu> menus = menuDao.findByParentIdAndRoleId(parentId, roleId);
        for (Menu menu : menus) {
            List<Menu> children = findChildrenByRole(menu.getId(), roleId);
            menu.setChildren(children);
        }
        return menus;
    }

    /**
     * 删除菜单,同时删除关联角色,子菜单
     *
     * @param id
     */
    public Boolean delete(Long id) {
        List<Menu> menus = menuDao.findByParentId(id);


        if (menus.size() > 0) {
            return false;
        }

        int i = roleMenuDao.deleteByMenuId(id);


        for (Menu menu : menus) {
            roleMenuDao.deleteByMenuId(menu.getId());//// TODO: 9/23/16 删除算法需要优化
        }

        menuDao.deleteByParentId(id);

        menuDao.delete(id);
        return true;
    }


}
