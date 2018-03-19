package com.magicears.bpm.controller;

import com.magicears.bpm.config.DingTalkConfig;
import com.magicears.bpm.dao.TestDao;
import com.magicears.bpm.util.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by admin on 2018/3/6.
 */
@RestController
@RequestMapping("test")
public class TestController {

    private final TestDao testDao;

    private final DingTalkConfig dingTalkConfig;


    @Autowired
    public TestController(TestDao testDao, DingTalkConfig dingTalkConfig) {
        this.testDao = testDao;
        this.dingTalkConfig = dingTalkConfig;
    }


    @RequestMapping("")
    public void test() {
//        String aa = dingTalkConfig.getAccessToken();
        String corpId = "ding9f3910490e50ec6935c2f4657eb6378f";
        String corpSecret = "Oj5EnaZCJBf2J5rcXQ9M3T2LWyECpD8nHbNGtnctdUOQ1AYJaq6ws1Fxtv4AWDk7";
        String url = "https://oapi.dingtalk.com/gettoken?corpid="+corpId+"&corpsecret="+corpSecret;
        try {
            String sr = HttpRequestUtil.doHttpGet(url);
            System.out.print(sr);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        System.out.print(aa);
    }
}
