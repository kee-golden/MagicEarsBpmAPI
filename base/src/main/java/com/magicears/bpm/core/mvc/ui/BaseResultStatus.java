package com.magicears.bpm.core.mvc.ui;

/**
 * Created by admin on 2018/3/7.
 */
public enum BaseResultStatus {

    SUCCESS(Integer.valueOf(1200), "成功"),
    ERROR_REQUEST(Integer.valueOf(1400), "错误的请求"),
    UNAUTHORIZED(Integer.valueOf(1401), "没有授权"),
    FORBIDDEN(Integer.valueOf(1403), "没有权限访问"),
    ERROR_USERNAME(Integer.valueOf(14031), "用户名错误"),
    ERROR_PASSWORD(Integer.valueOf(14032), "密码错误"),
    TOKEN_EXPIRED(Integer.valueOf(14033), "TOKEN过期"),
    NOT_FOND(Integer.valueOf(1404), "页面不存在"),
    SERVER_ERROR(Integer.valueOf(1500), "服务器错误"),
    USER_NOT_FINISH(1601, "用户信息未补全"),
    USER_NOT_AVAILABLE(1602, "用户被禁止");

    private Integer code;
    private String message;

    private BaseResultStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
