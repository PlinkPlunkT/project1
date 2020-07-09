package com.cskaoyan.project1.model.vo.orders;

import java.util.ArrayList;
import java.util.List;

/**
 * 修改订单时显示订单详情
 * @auther Ningbo Tien
 * @date 2020-07-09 15:16
 */
public class OrderDetailVO {

    private Integer id;

    private Double amount;

    private Integer num;

    //specId
    private Integer goodsDetailId;

    private Integer state;

    private String goods;

    private List<OrderSpecVO> spec;

    private Integer goodsId;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    private List<OrderStateVO> states = OrderDetailVO.setStates();
    static private List<OrderStateVO> setStates(){
        List<OrderStateVO> list = new ArrayList<>();
        list.add(new OrderStateVO(0, "未付款"));
        list.add(new OrderStateVO(1, "未发货"));
        list.add(new OrderStateVO(2, "已发货"));
        list.add(new OrderStateVO(3, "已完成订单"));
        return list;
    }

    private CurStateVO curState = new CurStateVO();

    private CurSpecVO curSpec = new CurSpecVO();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getGoodsDetailId() {
        return goodsDetailId;
    }

    public void setGoodsDetailId(Integer goodsDetailId) {
        this.goodsDetailId = goodsDetailId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public List<OrderSpecVO> getSpec() {
        return spec;
    }

    public void setSpec(List<OrderSpecVO> spec) {
        this.spec = spec;
    }

    public List<OrderStateVO> getStates() {
        return states;
    }

    public void setStates(List<OrderStateVO> states) {
        this.states = states;
    }

    public CurStateVO getcurState() {
        return curState;
    }

    public void setCurState() {
        curState.setId(state);
    }

    public CurSpecVO getcurSpec() {
        return curSpec;
    }

    public void setCurSpec() {
        curSpec.setId(goodsDetailId);
    }
}
