package com.magicears.bpm.dingtalk.js.entity;

import com.magicears.bpm.dingtalk.entity.ErroMessage;

public class SnsToken extends ErroMessage {

    private Integer expires_in;

    private String sns_token;

    public Integer getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Integer expires_in) {
        this.expires_in = expires_in;
    }

    public String getSns_token() {
        return sns_token;
    }

    public void setSns_token(String sns_token) {
        this.sns_token = sns_token;
    }
}
