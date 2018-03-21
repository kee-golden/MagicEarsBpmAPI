package com.magicears.bpm.controller;

import com.magicears.bpm.config.DingTalkConfig;
import com.magicears.bpm.dao.TestDao;
import com.magicears.bpm.dingtalk.entity.AccessToken;
import com.magicears.bpm.dingtalk.entity.Department;
import com.magicears.bpm.dingtalk.entity.Organization;
import com.magicears.bpm.service.DingTalkService;
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

    private final DingTalkService dingTalkService;


    @Autowired
    public TestController(TestDao testDao, DingTalkConfig dingTalkConfig, DingTalkService dingTalkService) {
        this.testDao = testDao;
        this.dingTalkConfig = dingTalkConfig;
        this.dingTalkService = dingTalkService;
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
//        String url = DingTalkUrl.userList + dingTalkConfig.getAccessToken() + "&department_id=1";
//        String userList = HttpRequestUtil.doHttpGet(url);


//        System.out.print(userList);
        // ab29d7fe8c1f301fac2638dc407e3bc0

//        AccessToken accessToken = dingTalkService.getAccessToken();

//        Organization organization = dingTalkService.getOrganization("ab29d7fe8c1f301fac2638dc407e3bc0");
        Department department = dingTalkService.getDepartment("ab29d7fe8c1f301fac2638dc407e3bc0",51463161L);


    }
}
