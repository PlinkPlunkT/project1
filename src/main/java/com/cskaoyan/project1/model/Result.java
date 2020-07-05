package com.cskaoyan.project1.model;

/**
 * 用来响应
 * @auther Ningbo Tien
 * @date 2020-07-03 21:16
 *
 */
public class Result {
    private Integer code;

    private String message;

    private Object data;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result() {
    }

    public Result(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }

    public Result(Integer code) {
        this.code = code;
    }

    public static Result ok(){
        return new Result(0);
    }

    public static Result ok(Object data){
        return new Result(0,data);
    }

    public static Result error(String message){
        return new Result(10000,message);
    }
}
