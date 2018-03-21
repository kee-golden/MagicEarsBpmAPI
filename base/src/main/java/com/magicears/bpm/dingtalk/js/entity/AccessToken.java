package com.magicears.bpm.dingtalk.js.entity;

import com.magicears.bpm.dingtalk.entity.ErroMessage;

public class AccessToken extends ErroMessage {


    /**
     * ACCESS_TOKEN
     */
    private String access_token;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
