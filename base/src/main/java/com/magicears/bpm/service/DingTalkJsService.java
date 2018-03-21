package com.magicears.bpm.service;

import com.alibaba.fastjson.JSON;
import com.magicears.bpm.config.DingTalkConfig;
import com.magicears.bpm.dingtalk.js.entity.AccessToken;
import com.magicears.bpm.dingtalk.js.entity.Persistent;
import com.magicears.bpm.dingtalk.url.DingTalkUrl;
import com.magicears.bpm.emuns.DingTalkErrCode;
import com.magicears.bpm.util.HttpRequestUtil;
import com.magicears.bpm.util.JsonUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * 钉钉js端service
 */
@Transactional
@Service
public class DingTalkJsService {

    private final DingTalkConfig dingTalkConfig;


    public DingTalkJsService(DingTalkConfig dingTalkConfig) {
        this.dingTalkConfig = dingTalkConfig;
    }





    /**
     * 获取钉钉开放应用的ACCESS_TOKEN
     * 并且获取返回成功数据
     *
     * @return
     */
    public AccessToken getAccessToken() {
        String accessTokenUrl = DingTalkUrl.snsToken + dingTalkConfig.getAppId() + "&appsecret=" + dingTalkConfig.getAppSecret();
        AccessToken accessToken = null;
        try {
            String accessTokenStr = HttpRequestUtil.doHttpGet(accessTokenUrl);
            accessToken = JsonUtil.jsonToBean(accessTokenStr, AccessToken.class);
            Integer errCode = accessToken.getErrcode();
            if (!errCode.equals(DingTalkErrCode.SUCCESS.getCode())) return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return accessToken;
    }


    /**
     * 获取用户授权的持久授权码tmp_auth_code
     * 并且获取返回成功数据
     *
     * @return
     */
    public Persistent getPersistent(String code, String accessToken) {
        Persistent persistent = null;
        Map<String, Object> parmMap = new HashMap<String, Object>();
        parmMap.put("tmp_auth_code", code);
        String persistentStr = HttpRequestUtil.doHttpPost(DingTalkUrl.tmpAuthCode + accessToken,
                JSON.toJSONString(parmMap));
        persistent = JsonUtil.jsonToBean(persistentStr,Persistent.class);
        if (!persistent.getErrcode().equals(DingTalkErrCode.SUCCESS.getCode())) return null;
        return persistent;
    }
}
