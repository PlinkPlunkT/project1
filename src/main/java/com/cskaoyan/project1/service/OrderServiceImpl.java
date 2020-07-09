package com.cskaoyan.project1.service;

import com.cskaoyan.project1.dao.OrderDao;
import com.cskaoyan.project1.dao.OrderDaoImpl;
import com.cskaoyan.project1.model.bo.orders.OrderChangeBO;
import com.cskaoyan.project1.model.bo.orders.OrdersByPageBO;
import com.cskaoyan.project1.model.vo.orders.OrderDetailVO;
import com.cskaoyan.project1.model.vo.orders.OrdersByPageInfoVO;
import com.cskaoyan.project1.model.vo.orders.OrdersByPageVO;

import java.util.List;

/**
 * @auther Ningbo Tien
 * @date 2020-07-08 21:44
 */
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();

    @Override
    public void deleteOrder(Integer id) {
        orderDao.deleteOrder(id);
    }

    @Override
    public void changeOrder(OrderChangeBO orderChangeBO) {
        //先得到新的spec
        String specContent = orderDao.getSpecContent(orderChangeBO.getSpec());
        orderDao.changeOrder(orderChangeBO, specContent);
    }

    @Override
    public OrderDetailVO order(Integer id) {

        //先得到除spec以外的其他数据
        OrderDetailVO orderDetailVO = orderDao.getOrder(id);
        orderDetailVO.setCurState();
        orderDetailVO.setCurSpec();
        //得到spec
        orderDetailVO.setSpec(orderDao.getSpec(orderDetailVO.getGoodsId()));

        return orderDetailVO;
    }

    /**
     * 根据传入的不同参数，执行不同的查询，返回对应的结果
     * @param ordersByPageBO
     * @return
     */
    @Override
    public OrdersByPageVO ordersByPage(OrdersByPageBO ordersByPageBO) {

        int totalCounts = orderDao.getTotalCounts(ordersByPageBO);

        //查询当前分页结果page1 1-5，page2 6-10 .。。
        List<OrdersByPageInfoVO> ordersByPageInfoVOS = orderDao.ordersByPage(ordersByPageBO);

        OrdersByPageVO ordersByPageVO = new OrdersByPageVO();
        ordersByPageVO.setTotal(totalCounts);
        ordersByPageVO.setOrders(ordersByPageInfoVOS);
        return ordersByPageVO;
    }
}
