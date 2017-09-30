package com.pavilion.domain;
public enum ErrorCode {
    OK(0, "成功"),
    ParameterError(10, "参数错误"),
    InternalServerError(500, "错误B"),
    Error(-1,"错误"),
    UnknownError(-999999,"未知错误");

    ErrorCode(int code, String description) {
        this.code = code;
        this.description = description;
    }
    private int code;
    private String description;
    public int getCode() {
        return code;
    }
    public String getDescription() {
        return description;
    }
}