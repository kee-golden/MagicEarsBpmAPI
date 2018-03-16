package com.magicears.bpm.service;

import com.magicears.bpm.dao.RoleDao;
import com.magicears.bpm.dao.RoleMenuDao;
import com.magicears.bpm.dao.RolePrivilegeDao;
import com.magicears.bpm.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by admin on 2018/3/15.
 */
@Transactional
@Service
public class RoleService {

    private final RoleDao roleDao;

    private final RolePrivilegeDao rolePrivilegeDao;

    private final RoleMenuDao roleMenuDao;

    @Autowired
    public RoleService(RoleDao roleDao, RolePrivilegeDao rolePrivilegeDao, RoleMenuDao roleMenuDao) {
        this.roleDao = roleDao;
        this.rolePrivilegeDao = rolePrivilegeDao;
        this.roleMenuDao = roleMenuDao;
    }


    /**
     * 保存
     *
     * @param role
     * @param privilege
     */
    public void save(Role role, Long[] privilege, Long[] menu) {
        Role target;
        if (null == role.getId()) {
            target = role;
            roleDao.insert(target);
        } else {
            target = roleDao.find(role.getId());
            target.setCode(role.getCode());
            target.setName(role.getName());
            target.setDescription(role.getDescription());
            roleDao.update(role);
        }
        if (privilege != null && privilege.length > 0) {
            rolePrivilegeDao.deleteByRoleId(role.getId());
            for (Long privilegeId : privilege) {
                RolePrivilege rolePrivilege = new RolePrivilege();
                Privilege p = new Privilege();
                p.setId(privilegeId);
                rolePrivilege.setPrivilege(p);
                rolePrivilege.setRole(role);
                rolePrivilegeDao.insert(rolePrivilege);
            }

        }

        if (menu != null && menu.length > 0) {
            roleMenuDao.deleteByRoleId(role.getId());
            for (Long menuId : menu) {
                RoleMenu roleMenu = new RoleMenu();
                Menu m = new Menu();
                m.setId(menuId);
                roleMenu.setMenu(m);
                roleMenu.setRole(role);

                roleMenuDao.insert(roleMenu);
            }

        }

    }

    public void delete(Long id) {
        rolePrivilegeDao.deleteByPrivilegeId(id);
        roleDao.deleteUserRolesByRoleId(id);
        roleDao.delete(id);
    }
}
