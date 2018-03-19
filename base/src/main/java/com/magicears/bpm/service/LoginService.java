package com.magicears.bpm.service;

import com.alibaba.fastjson.JSON;
import com.magicears.bpm.config.DingTalkConfig;
import com.magicears.bpm.dingtalk.entity.DingTalkMessage;
import com.magicears.bpm.dingtalk.url.DingTalkUrl;
import com.magicears.bpm.entity.User;
import com.magicears.bpm.util.HttpRequestUtil;
import com.magicears.bpm.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.lang.model.element.NestingKind;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Transactional
@Service
public class LoginService {

    private final DingTalkConfig dingTalkConfig;

    @Autowired
    public LoginService(DingTalkConfig dingTalkConfig) {
        this.dingTalkConfig = dingTalkConfig;
    }


    public User getDingTalkUser(String code){
        String accessToken = getAccessToken();



//        try {
//            String accessTokenStr = HttpRequestUtil.doHttpGet(accessTokenUrl);
//            Map<String,String> accessTokenMap = JsonUtil.jsonToMaps(accessTokenStr);
//            String accessToken = accessTokenMap.get("access_token");
//            System.out.print(accessToken);
//            Map<String, Object> parmMap = new HashMap<String, Object>();
//            parmMap.put("tmp_auth_code", code);
//            String tmpAuthCodeStr = HttpRequestUtil.doHttpPost(DingTalkUrl.tmpAuthCode + accessToken,
//                    JSON.toJSONString(parmMap));
//            System.out.print(tmpAuthCodeStr);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        User user = new User();
        return  user;
    }


    /**
     * 获取ACCESS_TOKEN
     * @return
     */
    public String getAccessToken () {
        String accessTokenUrl = DingTalkUrl.snsToken + dingTalkConfig.getAppId() +  "&appsecret=" + dingTalkConfig.getAppSecret();
        String accessToken = "";
        try {
            String  accessTokenStr  = HttpRequestUtil.doHttpGet(accessTokenUrl);
            DingTalkMessage dingTalkMessage = JsonUtil.jsonToBean(accessTokenStr,DingTalkMessage.class);
            if (dingTalkMessage.getErrCode().equals(0)) accessToken = dingTalkMessage.getObject().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return accessToken;
    }


    /**
     * 获取ACCESS_TOKEN
     * @return
     */
    public String getTmpAuthCode (String code, String accessToken) {
        String tmpAuthCode = "";
        Map<String, Object> parmMap = new HashMap<String, Object>();
        parmMap.put("tmp_auth_code", code);
        String tmpAuthCodeStr = HttpRequestUtil.doHttpPost(DingTalkUrl.tmpAuthCode + accessToken,
                JSON.toJSONString(parmMap));
        System.out.print(tmpAuthCodeStr);
        DingTalkMessage dingTalkMessage = JsonUtil.jsonToBean(tmpAuthCodeStr,DingTalkMessage.class);
        if (dingTalkMessage.getErrCode().equals(0)) tmpAuthCode = dingTalkMessage.getObject().toString();
        return tmpAuthCode;
    }

}
