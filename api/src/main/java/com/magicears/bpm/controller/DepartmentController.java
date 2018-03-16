package com.magicears.bpm.controller;

import com.magicears.bpm.core.mvc.ui.ResultData;
import com.magicears.bpm.dao.DepartmentDao;
import com.magicears.bpm.entity.Department;
import com.magicears.bpm.helper.mybatis.PageHelper;
import com.magicears.bpm.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by admin on 2018/3/15.
 */
@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentDao departmentDao;

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentDao departmentDao, DepartmentService departmentService) {
        this.departmentDao = departmentDao;
        this.departmentService = departmentService;
    }

    /**
     * 部门列表
     *
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResultData list(
            @RequestParam(defaultValue = "1", required = false) Integer page,
            @RequestParam(defaultValue = "20", required = false) Integer size,
            Department department) {
        PageHelper pageHelper = new PageHelper(page, size, null);
        List<Department> departmentList = departmentDao.findByPage(pageHelper,department);
        Integer totalAmount = departmentDao.findTotalAmount(department);
        return ResultData.ok().putDataValue("departmentList", departmentList).putDataValue("totalAmount", totalAmount);
    }


    /**
     * 添加部门
     *
     * @param department
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResultData save(Department department) {
        departmentDao.insert(department);
        return ResultData.ok();
    }

    /**
     * 获取部门
     *
     * @param departmentId
     * @return
     */
    @RequestMapping(value = "{departmentId}", method = RequestMethod.GET)
    public ResultData find(@PathVariable("departmentId") Long departmentId) {
        Department department = departmentDao.find(departmentId);
        return ResultData.ok().putDataValue("department", department);
    }

    /**
     * 获取部门
     *
     * @param department
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResultData find(Department department) {
        departmentDao.update(department);
        return ResultData.ok();
    }


    /**
     * 删除部门
     *
     * @return
     */
    @RequestMapping(value = "{departmentId}", method = RequestMethod.DELETE)
    public ResultData delete(@PathVariable("departmentId") Long departmentId) {
        boolean hasDelete = departmentService.delete(departmentId);
        if (hasDelete) {
            return ResultData.ok();
        } else {
            return new ResultData(2000,"");
        }
    }

}
