package com.magicears.bpm.emuns;

/**
 * Created by admin on 2018/3/6.
 */
public enum PrivilengeType {
    GET(1, "GET"),
    POST(2, "POST"),
    PUT(3, "PUT"),
    PATCH(4, "PATCH"),
    DELETE(5, "DELETE");

    PrivilengeType(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String getMessage(Integer code){
        String message = "";
        for (PrivilengeType status: values()){
            if (code.equals(status.getCode())) {
                message = status.getMessage();
            }
        }
        return message;
    }

    public static Integer getCode(String message){
        Integer code = 0;
        for (PrivilengeType status: values()){
            if (message.equals(status.getMessage())) {
                code = status.getCode();
            }
        }
        return code;
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
