package com.pavilion.util;

import com.pavilion.domain.Result;

public class ResultUtil {
    public static <T> Result<T> success(String msg, T data){
        return new Result(0,msg,data);
    }

    public static <T> Result<T> fail(int code,String msg, T data){
        return new Result(code,msg,data);
    }
}
