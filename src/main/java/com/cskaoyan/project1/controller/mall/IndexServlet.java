package com.cskaoyan.project1.controller.mall;

import com.cskaoyan.project1.model.Result;
import com.cskaoyan.project1.model.Type;
import com.cskaoyan.project1.model.vo.goods.TypeGoodsVO;
import com.cskaoyan.project1.service.GoodsService;
import com.cskaoyan.project1.service.GoodsServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 前端首页
 * @auther Ningbo Tien
 * @date 2020-07-09 21:02
 */
@WebServlet("/api/mall/index/*")
public class IndexServlet extends HttpServlet {
    
    private GoodsService goodsService = new GoodsServiceImpl();
    private Gson gson = new Gson();
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/mall/index/","");
        if("getType".equals(action)){
            getType(request, response);
        }
    }

    private void getType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Type> typeList = goodsService.getType();
        response.getWriter().println(gson.toJson(Result.ok(typeList)));
    }
}
