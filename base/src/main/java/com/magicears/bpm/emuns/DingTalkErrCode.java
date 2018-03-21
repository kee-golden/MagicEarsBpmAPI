package com.magicears.bpm.emuns;

public enum  DingTalkErrCode {

    SUCCESS(0, "SUCCESS"),
    FAIL(1, "FAIL");

    DingTalkErrCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
