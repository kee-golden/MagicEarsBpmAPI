package com.magicears.bpm.dingtalk.entity;

public class ErroMessage {


    //钉钉返回码
    private Integer errcode;

    //消息
    private String errmsg;

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
