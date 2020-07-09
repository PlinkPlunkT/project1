package com.cskaoyan.project1.model.vo.orders;

import com.cskaoyan.project1.model.enumaration.OrderState;

/**
 * 后台管理系统分页查询订单每个订单详情VO
 * 在OrdersByPageVO中使用
 * @auther Ningbo Tien
 * @date 2020-07-08 22:16
 */
public class OrdersByPageInfoVO {
    private Integer id;

    private Integer userId;

    private Integer goodsDetailId;

    private String goods;

    private String spec;

    private Integer goodsNum;

    private Double amount;

    private Integer stateId;

    private String state;

    private OrdersByPageUserVO user = new OrdersByPageUserVO();

    //处理user对象
    public String getNickname() {
        return user.getNickname();
    }

    public void setNickname(String nickname) {
        user.setNickname(nickname);
    }

    public String getName() {
        return user.getName();
    }

    public void setName(String name) {
        user.setName(name);
    }

    public String getAddress() {
        return user.getAddress();
    }

    public void setAddress(String address) {
        user.setAddress(address);
    }

    public String getPhone() {
        return user.getPhone();
    }

    public void setPhone(String phone) {
        user.setPhone(phone);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGoodsDetailId() {
        return goodsDetailId;
    }

    public void setGoodsDetailId(Integer goodsDetailId) {
        this.goodsDetailId = goodsDetailId;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getStateId() {
        return stateId;
    }

    /**
     * 在这里做一些处理：
     * @param stateId
     */
    public void setStateId(Integer stateId) {

        if(stateId.equals(OrderState.UN_PAID.getCode())){
            setState(OrderState.UN_PAID.getValue());
        }
        if(stateId.equals(OrderState.UN_SHIPED.getCode())){
            setState(OrderState.UN_SHIPED.getValue());
        }
        if(stateId.equals(OrderState.DELIVERED.getCode())){
            setState(OrderState.DELIVERED.getValue());
        }
        if(stateId.equals(OrderState.RECEIVED.getCode())){
            setState(OrderState.RECEIVED.getValue());
        }

        this.stateId = stateId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public OrdersByPageUserVO getUser() {
        return user;
    }

    public void setUser(OrdersByPageUserVO user) {
        this.user = user;
    }
}
