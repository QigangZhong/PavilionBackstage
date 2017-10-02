package com.pavilion.domain.dto;

import com.pavilion.domain.ErrorCode;
import com.pavilion.domain.Result;

public class TableData<T> {
    private int code;
    private String msg;
    private int count;
    private T data;

    public TableData(){
        this.code=0;
        this.msg="";
        this.count=0;
        this.data=null;
    }

    public TableData(int code,String msg,int count, T data){
        this.code=code;
        this.msg=msg;
        this.count=count;
        this.data=data;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
