package com.markerhub.common.lang;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {

    private int code; // 200 = 正常
    private String message;
    private Object data;

    public static Result success(Object data) {
        return success(200, "操作成功", data);
    }

    public static Result success(int code, String message, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setMessage(message);
        r.setData(data);
        return r;
    }

    public static Result fail(String message) {
        return fail(400, message, null);
    }

    public static Result fail(String message, Object data) {
        return fail(400, message, data);
    }

    public static Result fail(int code, String message, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setMessage(message);
        r.setData(data);
        return r;
    }
}
