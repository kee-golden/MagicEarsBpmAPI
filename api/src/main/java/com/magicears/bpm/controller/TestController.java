package com.magicears.bpm.controller;

import com.magicears.bpm.entity.Test;
import com.magicears.bpm.dao.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by admin on 2018/3/6.
 */
@RestController
@RequestMapping("test1")
public class TestController {

    private final TestDao testDao;


    @Autowired
    public TestController(TestDao testDao) {
        this.testDao = testDao;
    }


    @RequestMapping("")
    public void test() {
        Test test = testDao.find(1L);
        System.out.println(test.getId());
    }
}
