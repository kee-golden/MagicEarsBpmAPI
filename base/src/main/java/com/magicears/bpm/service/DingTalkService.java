package com.magicears.bpm.service;

import com.alibaba.fastjson.JSON;
import com.magicears.bpm.config.DingTalkConfig;
import com.magicears.bpm.dingtalk.url.DingTalkUrl;
import com.magicears.bpm.emuns.DingTalkErrCode;
import com.magicears.bpm.util.HttpRequestUtil;
import com.magicears.bpm.util.JsonUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Transactional
@Service
public class DingTalkService {

    private final DingTalkConfig dingTalkConfig;

    public DingTalkService(DingTalkConfig dingTalkConfig) {
        this.dingTalkConfig = dingTalkConfig;
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
            Map<String,Object> map = JsonUtil.jsonToMaps(accessTokenStr);
            Integer errCode = (Integer) map.get("errCode");
            if (errCode.equals(DingTalkErrCode.SUCCESS.getCode())) accessToken = map.get("access_token").toString();
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
        Map<String,Object> map = JsonUtil.jsonToMaps(tmpAuthCodeStr);
        Integer errCode = (Integer) map.get("errCode");
        if (errCode.equals(DingTalkErrCode.SUCCESS.getCode())) tmpAuthCode = map.get("tmp_auth_code").toString();
        return tmpAuthCode;
    }

}
