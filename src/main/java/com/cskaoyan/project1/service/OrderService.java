package com.cskaoyan.project1.service;

import com.cskaoyan.project1.model.bo.orders.OrderChangeBO;
import com.cskaoyan.project1.model.bo.orders.OrdersByPageBO;
import com.cskaoyan.project1.model.vo.orders.OrderDetailVO;
import com.cskaoyan.project1.model.vo.orders.OrdersByPageVO;

/**
 * @auther Ningbo Tien
 * @date 2020-07-08 21:46
 */
public interface OrderService {
    OrdersByPageVO ordersByPage(OrdersByPageBO ordersByPageBO);

    OrderDetailVO order(Integer id);

    void changeOrder(OrderChangeBO orderChangeBO);

    void deleteOrder(Integer id);
}
