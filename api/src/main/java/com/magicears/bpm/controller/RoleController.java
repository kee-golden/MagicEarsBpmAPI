package com.magicears.bpm.controller;

import com.magicears.bpm.core.mvc.ui.ResultData;
import com.magicears.bpm.dao.RoleDao;
import com.magicears.bpm.entity.Menu;
import com.magicears.bpm.entity.Privilege;
import com.magicears.bpm.entity.Role;
import com.magicears.bpm.service.MenuService;
import com.magicears.bpm.service.PrivilegeService;
import com.magicears.bpm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by admin on 2018/3/15.
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    private final RoleDao roleDao;

    private final RoleService roleService;

    private final PrivilegeService privilegeService;

    private final MenuService menuService;


    @Autowired
    public RoleController(RoleDao roleDao, RoleService roleService, PrivilegeService privilegeService, MenuService menuService) {
        this.roleDao = roleDao;
        this.roleService = roleService;
        this.privilegeService = privilegeService;
        this.menuService = menuService;
    }


    /**
     * 角色列表
     *
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResultData list(Model model) {
        List<Role> roleList = roleDao.findAll();
        return ResultData.ok().putDataValue("roleList", roleList);
    }

    /**
     * 保存角色
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResultData save(Role role, @RequestParam(value = "privilege", required = false) Long[] privileges, @RequestParam(value = "menu", required = false) Long[] menus) {
        roleService.save(role, privileges, menus);
        return ResultData.ok();
    }

    /**
     * 获取角色
     *
     * @return
     */
    @RequestMapping(value = "{roleId}", method = RequestMethod.GET)
    public ResultData edit(@PathVariable("roleId") Long roleId) {
        Role role = roleDao.find(roleId);
        return ResultData.ok().putDataValue("role", role);
    }


    /**
     * 角色权限列表
     *
     * @return
     */
    @RequestMapping(value = "{roleId}/privilege", method = RequestMethod.GET)
    public ResultData privileges(@PathVariable("roleId") Long roleId) {
        List<Privilege> privileges = privilegeService.findAllByRoleId(roleId);
        return ResultData.ok().putDataValue("privileges", privileges);
    }


    /**
     * 更新角色
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "{roleId}", method = RequestMethod.PUT)
    public ResultData update(
            Role role,
            @RequestParam(value = "privilege", required = false) Long[] privileges,
            @RequestParam(value = "menu", required = false) Long[] menus) {
        roleService.save(role, privileges, menus);
        return ResultData.ok();
    }


    /**
     * 角色菜单列表
     *
     * @return
     */
    @RequestMapping(value = "{roleId}/menu", method = RequestMethod.GET)
    public ResultData menus(@PathVariable("roleId") Long roleId) {
        List<Menu> menus = menuService.findAllByRoleId(roleId);
        return ResultData.ok().putDataValue("menus", menus);
    }

    /**
     * 删除角色
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "{roleId}", method = RequestMethod.DELETE)
    public ResultData delete(@PathVariable("roleId") Long roleId) {
        roleService.delete(roleId);
        return ResultData.ok();
    }
}
