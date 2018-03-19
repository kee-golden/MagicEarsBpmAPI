package com.magicears.bpm.dingtalk.entity;

public class Persistent {

    private String openid;

    private String persistentCode;


    public Persistent(String openid, String persistentCode) {
        this.openid = openid;
        this.persistentCode = persistentCode;
    }


    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getPersistentCode() {
        return persistentCode;
    }

    public void setPersistentCode(String persistentCode) {
        this.persistentCode = persistentCode;
    }
}
