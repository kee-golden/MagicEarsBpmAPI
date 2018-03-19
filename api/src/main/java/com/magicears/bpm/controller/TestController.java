package com.magicears.bpm.controller;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.magicears.bpm.config.DingTalkConfig;
import com.magicears.bpm.dao.TestDao;
import com.magicears.bpm.dingtalk.url.DingTalkUrl;
import com.magicears.bpm.util.HttpRequestUtil;
import com.magicears.bpm.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

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
    public void test() throws IOException {
//        String corpId = "ding9f3910490e50ec6935c2f4657eb6378f";
//        String corpSecret = "Oj5EnaZCJBf2J5rcXQ9M3T2LWyECpD8nHbNGtnctdUOQ1AYJaq6ws1Fxtv4AWDk7";
//        String url = "https://oapi.dingtalk.com/gettoken?corpid="+corpId+"&corpsecret="+corpSecret;
//        try {
//            String tokenStr = HttpRequestUtil.doHttpGet(url);
//            Map<String,Object> tokenJsonMap = JsonUtil.jsonToMaps(tokenStr);
//            System.out.print(tokenStr);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        String url = DingTalkUrl.departmentList + dingTalkConfig.getAccessToken();
//        String departmentLisStr = HttpRequestUtil.doHttpGet(url);
        String url = DingTalkUrl.userList + dingTalkConfig.getAccessToken() + "&department_id=1";
        String userList = HttpRequestUtil.doHttpGet(url);


        System.out.print(userList);
    }
}
