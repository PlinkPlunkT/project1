package com.cskaoyan.project1.model;

/**
 * @auther Ningbo Tien
 * @date 2020-07-03 21:16
 * 用来响应
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
}
