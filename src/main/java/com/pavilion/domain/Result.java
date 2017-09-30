package com.pavilion.domain;

public class Result<T> {
    private int code;
    private String msg;
    private T data;

    public Result(){
        this.code=0;
        this.msg="";
        this.data=null;
    }

    public Result(int code,String msg,T data){
        this.code=code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> Result<T> success(String msg, T data){
        return new Result(0,msg,data);
    }

    public static <T> Result<T> fail(int code,String msg,T data){
        return new Result(code,msg,data);
    }
}
