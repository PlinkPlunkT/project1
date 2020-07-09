package com.cskaoyan.project1.controller.mall;

import com.cskaoyan.project1.model.Result;
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
 * @auther Ningbo Tien
 * @date 2020-07-09 23:56
 */
@WebServlet("/api/mall/goods/*")
public class GoodsServlet extends HttpServlet {

    private GoodsService goodsService = new GoodsServiceImpl();
    private Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/mall/goods/","");
        if("getGoodsByType".equals(action)){
            getGoodsByType(request, response);
        }
    }

    /**
     * 首页的getGoodsByType请求
     * @param request
     * @param response
     * @throws IOException
     */
    private void getGoodsByType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String typeId = request.getParameter("typeId");
        List<TypeGoodsVO> typeGoodsVOList = goodsService.getGoodsByType(typeId);
        response.getWriter().println(gson.toJson(Result.ok(typeGoodsVOList)));

    }
}
