package com.cskaoyan.project1.model.vo.orders;

import com.cskaoyan.project1.model.vo.orders.OrdersByPageInfoVO;

import java.util.List;

/**
 * 在ordersByPage方法中使用
 * @auther Ningbo Tien
 * @date 2020-07-08 22:10
 */
public class OrdersByPageVO {

    private Integer total;

    private List<OrdersByPageInfoVO> orders;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<OrdersByPageInfoVO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrdersByPageInfoVO> orders) {
        this.orders = orders;
    }
}
