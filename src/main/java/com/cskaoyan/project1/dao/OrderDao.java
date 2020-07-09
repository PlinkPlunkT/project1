package com.cskaoyan.project1.dao;

import com.cskaoyan.project1.model.bo.orders.OrderChangeBO;
import com.cskaoyan.project1.model.bo.orders.OrdersByPageBO;
import com.cskaoyan.project1.model.vo.orders.OrderDetailVO;
import com.cskaoyan.project1.model.vo.orders.OrderSpecVO;
import com.cskaoyan.project1.model.vo.orders.OrdersByPageInfoVO;

import java.util.List;

/**
 * @auther Ningbo Tien
 * @date 2020-07-08 22:38
 */
public interface OrderDao {
    List<OrdersByPageInfoVO> ordersByPage(OrdersByPageBO ordersByPageBO);

    int getTotalCounts(OrdersByPageBO ordersByPageBO);

    OrderDetailVO getOrder(Integer id);

    List<OrderSpecVO> getSpec(Integer goodsId);

    void changeOrder(OrderChangeBO orderChangeBO, String specContent);

    String getSpecContent(Integer spec);

    void deleteOrder(Integer id);
}
