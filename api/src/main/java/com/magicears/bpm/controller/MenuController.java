package com.magicears.bpm.controller;

import com.magicears.bpm.core.mvc.ui.ResultData;
import com.magicears.bpm.dao.MenuDao;
import com.magicears.bpm.entity.Menu;
import com.magicears.bpm.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by admin on 2018/3/15.
 */
@RestController
@RequestMapping("/menu")
public class MenuController {


    private final MenuService menuService;

    private final MenuDao menuDao;

    @Autowired
    public MenuController(MenuService menuService, MenuDao menuDao) {
        this.menuService = menuService;
        this.menuDao = menuDao;
    }


    /**
     * 获取所有菜单
     *
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResultData list() {
        List<Menu> menuList = menuService.findAll();
        return ResultData.ok().putDataValue("menuList", menuList);
    }


    /**
     * 插入新菜单
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResultData insert(Menu menu, Long parentId) {
        Menu target;
        Menu parent = new Menu();
        if (parentId != null) {
            parent = menuDao.find(parentId);
        }
        target = menu;
        target.setParent(parent);
        target.setCreateAt(new Date());
        target.setUpdateAt(new Date());
        target.setTarget(menu.getTarget());
        menuDao.insert(target);
        target = menuDao.find(target.getId());
        target.setSort(target.getId());//设置排序
        menuDao.update(target);

        return ResultData.ok();
    }

    /**
     * 获取菜单
     *
     * @return
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResultData get(@PathVariable("id") Long id) {
        Menu menu = menuService.find(id);
        return ResultData.ok().putDataValue("menu", menu);
    }


    /**
     * 更新菜单
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{menuId}", method = RequestMethod.PUT)
    public ResultData update(Menu menu, @PathVariable("menuId") Long menuId, Long parentId) {
        Menu target;
        Menu parent = new Menu();
        if (parentId != null) {
            parent = menuDao.find(parentId);
        }
        target = menuDao.find(menu.getId());
        target.setAction(menu.getAction());
        target.setCode(menu.getCode());
        target.setTarget(menu.getTarget());
        target.setName(menu.getName());
        target.setType(menu.getType());
        target.setParent(parent);
        target.setUpdateAt(new Date());
        menuDao.update(menu);
        return ResultData.ok();
    }

    /**
     * 删除菜单
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{menuId}", method = RequestMethod.DELETE)
    public ResultData delete(@PathVariable("menuId") Long menuId) {
        Boolean success = menuService.delete(menuId);
        return ResultData.ok();
    }

}
