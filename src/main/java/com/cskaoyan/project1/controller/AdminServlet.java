package com.cskaoyan.project1.controller;

import com.cskaoyan.project1.model.Admin;
import com.cskaoyan.project1.model.bo.AdminLoginBO;
import com.cskaoyan.project1.model.Result;
import com.cskaoyan.project1.model.vo.AdminLoginVO;
import com.cskaoyan.project1.service.AdminService;
import com.cskaoyan.project1.service.AdminServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @auther Ningbo Tien
 * @date 2020-07-03 17:50
 */
@WebServlet("/api/admin/admin/*")
public class AdminServlet extends HttpServlet {

    private AdminService adminService = new AdminServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/admin", "");
        if("login".equals(action)){
            login(request,response);
        }
    }

    /**
     * 管理员登陆的具体业务逻辑
     * 1.浏览器向8084发送一个请求，请求体中携带了用户名密码参数（json字符串形式）
     * 2.查询数据库，校验当前用户名密码是否正确
     * 3.根据结果返回不同的响应
     * @param request
     * @param response
     */
    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //取出请求参数，取出请求体里面的数据
        //此时无法使用request.getParameter key=value&key1=value,手动解析
        ServletInputStream inputStream = request.getInputStream();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int length = 0;
        while((length = inputStream.read(bytes)) != -1){
            outputStream.write(bytes, 0, length);
        }

        //请求体中的参数转为Java对象
        String requestBody = outputStream.toString("utf-8");
        Gson gson = new Gson();
        AdminLoginBO loginBO = gson.fromJson(requestBody, AdminLoginBO.class);
//        response.getWriter().println(loginBO);
        //该查询数据库，分层，新建service和dao
        //根据login的结果，返回不同的响应
        Admin login = adminService.login(loginBO);
        Result result = new Result();
        if(login != null){
            result.setCode(0);
            AdminLoginVO loginVO = new AdminLoginVO();
            loginVO.setToken(login.getNickname());
            loginVO.setName(login.getNickname());
            result.setData(loginVO);
        }else{
            result.setCode(10000);
            result.setMessage("登陆失败！请确认用户名和密码！！");
        }

        response.getWriter().println(gson.toJson(result));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
