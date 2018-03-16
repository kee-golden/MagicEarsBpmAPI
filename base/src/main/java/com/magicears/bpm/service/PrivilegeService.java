package com.magicears.bpm.service;

import com.magicears.bpm.dao.PrivilegeDao;
import com.magicears.bpm.dao.RolePrivilegeDao;
import com.magicears.bpm.entity.Privilege;
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
public class PrivilegeService {

    private final PrivilegeDao privilegeDao;

    private final RolePrivilegeDao rolePrivilegeDao;

    @Autowired
    public PrivilegeService(PrivilegeDao privilegeDao, RolePrivilegeDao rolePrivilegeDao) {
        this.privilegeDao = privilegeDao;
        this.rolePrivilegeDao = rolePrivilegeDao;
    }


    /**
     * 获取全部菜单树
     *
     * @return
     */
    public List<Privilege> findAll() {
        List<Privilege> parents = privilegeDao.findParents();
        for (Privilege parent : parents) {
            List<Privilege> children = findChildren(parent.getId());
            parent.setChildren(children);
        }
        return parents;
    }


    /**
     * 获取子菜单
     *
     * @param parentId
     * @return
     */
    public List<Privilege> findChildren(Long parentId) {

        List<Privilege> menus = privilegeDao.findByParentId(parentId);
        for (Privilege menu : menus) {
            List<Privilege> children = findChildren(menu.getId());
            menu.setChildren(children);
        }

        return menus;
    }


    public Privilege find(Long id) {
        Privilege privilege = privilegeDao.find(id);
        if (privilege.getParent() != null) {

        }
        return privilege;
    }



    /**
     * 通过角色获取权限
     *
     * @param roleId
     * @return
     */
    public List<Privilege> findAllByRoleId(Long roleId) {
        List<Privilege> parents = privilegeDao.findParentsByRoleId(roleId);
        List<Privilege> privileges = new ArrayList<>(parents);
        for (Privilege parent : parents) {
            List<Privilege> children = findChildrenByRole(parent.getId(), roleId);
            privileges.addAll(children);
        }
        return privileges;
    }


    /**
     * 获取子菜单
     *
     * @param parentId
     * @param roleId
     * @return
     */
    public List<Privilege> findChildrenByRole(Long parentId, Long roleId) {
        List<Privilege> menus = privilegeDao.findByParentIdAndRoleId(parentId, roleId);
        return menus;
    }


    /**
     * 删除菜单,同时删除关联角色,子菜单
     *
     * @param id
     */
    public Boolean delete(Long id) {
        List<Privilege> menus = privilegeDao.findByParentId(id);

        if (menus.size() > 0) {
            return false;
        }

        int i = rolePrivilegeDao.deleteByPrivilegeId(id);


        for (Privilege menu : menus) {
            rolePrivilegeDao.deleteByPrivilegeId(menu.getId());//// TODO: 9/23/16 删除算法需要优化
        }

        privilegeDao.deleteByParentId(id);

        privilegeDao.delete(id);
        return true;
    }
}
