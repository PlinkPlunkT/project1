package com.cskaoyan.project1.controller.admin;

import com.cskaoyan.project1.model.Result;
import com.cskaoyan.project1.model.bo.orders.OrderChangeBO;
import com.cskaoyan.project1.model.bo.orders.OrdersByPageBO;
import com.cskaoyan.project1.model.vo.orders.OrderDetailVO;
import com.cskaoyan.project1.model.vo.orders.OrdersByPageVO;
import com.cskaoyan.project1.service.OrderService;
import com.cskaoyan.project1.service.OrderServiceImpl;
import com.cskaoyan.project1.utils.HttpUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 订单管理
 * @auther Ningbo Tien
 * @date 2020-07-08 21:24
 */
@WebServlet("/api/admin/order/*")
public class OrdersServlet extends HttpServlet {

    private OrderService orderService = new OrderServiceImpl();
    private Gson gson = new Gson();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/order/","");
        if("ordersByPage".equals(action)){
            ordersByPage(request, response);
        }else if("changeOrder".equals(action)){
            changeOrder(request, response);
        }
    }

    /**
     * 保存对订单的修改
     * @param request
     * @param response
     */
    private void changeOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        OrderChangeBO orderChangeBO = gson.fromJson(requestBody, OrderChangeBO.class);
        orderService.changeOrder(orderChangeBO);
        response.getWriter().println(gson.toJson(Result.ok("修改成功！")));
    }

    /**
     * 根据条件分页查询显示订单
     * 1.获取请求参数；
     * 2.业务逻辑处理；
     * 3.响应；
     * @param request
     * @param response
     */
    private void ordersByPage(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String requestBody = HttpUtils.getRequestBody(request);
        OrdersByPageBO ordersByPageBO = gson.fromJson(requestBody, OrdersByPageBO.class);
        OrdersByPageVO ordersByPageVO = orderService.ordersByPage(ordersByPageBO);
        response.getWriter().println(gson.toJson(Result.ok(ordersByPageVO)));

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/order/","");
        if("order".equals(action)){
            order(request, response);
        }else if("deleteOrder".equals(action)){
            deleteOrder(request, response);
        }
    }

    /**
     * 删除指定id的订单
     * @param request
     * @param response
     */
    private void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        orderService.deleteOrder(id);
        response.getWriter().println(gson.toJson(Result.ok("删除成功！")));
    }

    /**
     * 修改订单时显示订单详情
     * 通过订单id得到
     * @param request
     * @param response
     */
    private void order(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Integer id = Integer.parseInt(request.getParameter("id")) ;
        OrderDetailVO orderDetailVO = orderService.order(id);
        response.getWriter().println(gson.toJson(Result.ok(orderDetailVO)));
    }
}
