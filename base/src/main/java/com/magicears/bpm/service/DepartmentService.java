package com.magicears.bpm.service;

import com.magicears.bpm.dao.DepartmentDao;
import com.magicears.bpm.dao.UserDao;
import com.magicears.bpm.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by admin on 2018/3/15.
 */
@Transactional
@Service
public class DepartmentService {

    private final UserDao userDao;
    private final DepartmentDao departmentDao;

    @Autowired
    public DepartmentService(UserDao userDao, DepartmentDao departmentDao) {
        this.userDao = userDao;
        this.departmentDao = departmentDao;
    }


    /**
     * 删除
     * @param id
     * @return
     */
    public Boolean delete(Long id) {
        List<User> userList = userDao.findByDepartmentId(id);
        if (userList.size() > 0) {
            return  false;
        }
        departmentDao.delete(id);
        return true;
    }
}
