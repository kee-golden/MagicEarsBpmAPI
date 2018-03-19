package com.magicears.bpm.dingtalk.entity;

public class DingTalkMessage {

    private Integer errCode;

    private String errMsg;

    private Object object;


    public DingTalkMessage(Object object) {
        this.object = object;
    }

    public DingTalkMessage() {
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }



}
