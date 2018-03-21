package com.magicears.bpm.dingtalk.js.entity;

import com.magicears.bpm.dingtalk.entity.ErroMessage;

public class Persistent extends ErroMessage {

    private String openid;

    private String persistent_code;

    private String unionid;


    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getPersistent_code() {
        return persistent_code;
    }

    public void setPersistent_code(String persistent_code) {
        this.persistent_code = persistent_code;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }
}
