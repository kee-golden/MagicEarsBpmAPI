package com.magicears.bpm.controller;

import com.magicears.bpm.core.mvc.ui.ResultData;
import com.magicears.bpm.dao.PrivilegeDao;
import com.magicears.bpm.entity.Privilege;
import com.magicears.bpm.service.PrivilegeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by admin on 2018/3/15.
 */

@RestController
@RequestMapping("/privilege")
public class PrivilegeController {

    private static final Logger logger = LoggerFactory.getLogger(PrivilegeController.class);

    private final PrivilegeService privilegeService;

    private final PrivilegeDao privilegeDao;

    @Autowired
    public PrivilegeController(PrivilegeService privilegeService, PrivilegeDao privilegeDao) {
        this.privilegeService = privilegeService;
        this.privilegeDao = privilegeDao;
    }


    /**
     * 获取所有权限
     *
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResultData list() {
        List<Privilege> privilegeList = privilegeService.findAll();
        return ResultData.ok().putDataValue("privilegeList", privilegeList);
    }


    /**
     * 插入新权限
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResultData insert(Privilege privilege, Long parentId) {
        Privilege target;
        Privilege parent = new Privilege();

        if (parentId != null) {
            parent = privilegeDao.find(parentId);
        }
        target = privilege;
        target.setParent(parent);
        target.setCreateAt(new Date());
        target.setUpdateAt(new Date());
        privilegeDao.insert(target);
        target = privilegeDao.find(target.getId());
        target.setSort(target.getId());//设置排序
        privilegeDao.update(target);

        return ResultData.ok();
    }


    /**
     * 获取权限
     *
     * @return
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResultData get(@PathVariable("id") Long id) {
        Privilege privilege = privilegeService.find(id);
        return ResultData.ok().putDataValue("privilege", privilege);
    }

    /**
     * 更新纪录
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{privilegeId}", method = RequestMethod.PUT)
    public ResultData update(Privilege privilege, @PathVariable("privilegeId") Long privilegeId, Long parentId) {
        Privilege target;
        Privilege parent = new Privilege();
        if (parentId != null) {
            parent = privilegeDao.find(parentId);
        }
        target = privilegeDao.find(privilege.getId());
        target.setAction(privilege.getAction());
        target.setCode(privilege.getCode());
        target.setName(privilege.getName());
        target.setType(privilege.getType());
        target.setParent(parent);
        target.setUpdateAt(new Date());
        privilegeDao.update(privilege);
        return ResultData.ok();
    }



    /**
     * 删除
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{privilegeId}", method = RequestMethod.DELETE)
    public ResultData delete(@PathVariable("privilegeId") Long privilegeId) {
        Boolean success = privilegeService.delete(privilegeId);
        return ResultData.ok();

    }



}
