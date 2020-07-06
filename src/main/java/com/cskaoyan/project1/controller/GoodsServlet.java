package com.cskaoyan.project1.controller;

import com.cskaoyan.project1.model.Result;
import com.cskaoyan.project1.model.bo.ReplyBO;
import com.cskaoyan.project1.model.vo.MsgVO;
import com.cskaoyan.project1.service.GoodsService;
import com.cskaoyan.project1.service.GoodsServiceImpl;
import com.cskaoyan.project1.utils.HttpUtils;
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
 * @date 2020-07-06 16:12
 */
@WebServlet("/api/admin/goods/*")
public class GoodsServlet extends HttpServlet {

    private GoodsService goodsService = new GoodsServiceImpl();
    private Result result = new Result();
    private Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/goods/", "");
        if("reply".equals(action)){
            reply(request, response);
        }
    }

    /**
     * 回复留言
     * @param request
     * @param response
     */
    private void reply(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String requestBody = HttpUtils.getRequestBody(request);
        ReplyBO replyBO = gson.fromJson(requestBody, ReplyBO.class);
        goodsService.reply(replyBO);
        response.getWriter().println(gson.toJson(Result.ok("已回复该用户！")));

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/goods/", "");

        if("noReplyMsg".equals(action)){
            noReplyMsg(request, response);
        }else if("repliedMsg".equals(action)){
            repliedMsg(request, response);
        }
    }

    //显示已经回复的留言
    private void repliedMsg(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<MsgVO> msgVOList = goodsService.repliedMsg();
        response.getWriter().println(gson.toJson(Result.ok(msgVOList)));
    }

    /**
     * 显示所有未回复的留言
     * @param request
     * @param response
     */
    private void noReplyMsg(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //返回的未回复留言是一个集合
        List<MsgVO> msgVOList = goodsService.noReplyMsg();
        result.setCode(0);
        result.setData(msgVOList);
        response.getWriter().println(gson.toJson(result));
    }
}
