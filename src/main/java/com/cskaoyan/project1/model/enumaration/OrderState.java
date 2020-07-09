package com.cskaoyan.project1.model.enumaration;

/**
 * 订单状态
 * @auther Ningbo Tien
 * @date 2020-07-09 11:52
 */
public enum OrderState {

    UN_PAID(0, "未付款"),

    UN_SHIPED(1, "未发货"),

    DELIVERED(2, "已发货"),

    RECEIVED(3, "已到货");

    private Integer code;

    private String value;

    OrderState(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }}
