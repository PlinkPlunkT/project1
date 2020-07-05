package com.cskaoyan.project1.controller;

import com.cskaoyan.project1.model.Admin;
import com.cskaoyan.project1.model.bo.AdminChangePwdBO;
import com.cskaoyan.project1.model.bo.AdminLoginBO;
import com.cskaoyan.project1.model.Result;
import com.cskaoyan.project1.model.vo.AdminLoginVO;
import com.cskaoyan.project1.service.AdminService;
import com.cskaoyan.project1.service.AdminServiceImpl;
import com.cskaoyan.project1.utils.HttpUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @auther Ningbo Tien
 * @date 2020-07-03 17:50
 */
@WebServlet("/api/admin/admin/*")
public class AdminServlet extends HttpServlet {

    private AdminService adminService = new AdminServiceImpl();
    private Result result = new Result();
    Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/admin/", "");
        if("login".equals(action)){
            login(request,response);
        }else if("addAdminss".equals(action)){
            addAdmins(request,response);
        }else if("updateAdminss".equals(action)){
            updateAdmins(request,response);
        }else if("getSearchAdmins".equals(action)){
            getSearchAdmins(request, response);
        }else if("changePwd".equals(action)){
            changePwd(request, response);
        }
    }

    /**
     * 更改当前用户密码
     * @param request
     * @param response
     */
    private void changePwd(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String requestBody = HttpUtils.getRequestBody(request);
        AdminChangePwdBO adminChangePwdBO = gson.fromJson(requestBody, AdminChangePwdBO.class);
        String message = adminService.changePwd(adminChangePwdBO);
        if("修改成功！".equals(message)){
            result.setCode(0);
        }else {
            result.setCode(10000);
        }
        result.setMessage(message);
        response.getWriter().println(gson.toJson(result));
    }

    /**
     * 根据管理员账户和管理员昵称查找管理员
     * @param request
     * @param response
     */
    private void getSearchAdmins(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String requestBody = HttpUtils.getRequestBody(request);
        Admin admin = gson.fromJson(requestBody,Admin.class);
        List<Admin> adminList = adminService.getSearchAdmins(admin);
        result.setCode(0);
        result.setData(adminList);
        response.getWriter().println(gson.toJson(result));

    }

    /**
     * 修改管理员账号密码及昵称
     * 1.判断修改后的昵称与账号是否已存在
     * 2.进行修改
     * @param request
     * @param response
     */
    private void updateAdmins(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String requestBody = HttpUtils.getRequestBody(request);
        //用json将requestBody转为Admin对象
        Admin admin = gson.fromJson(requestBody, Admin.class);
        String message = adminService.updateAdminss(admin);
        if(" ".equals(message)){
            result.setCode(0);
            result.setMessage("修改成功！");
        }else{
            result.setCode(10000);
            result.setMessage(message);
        }

        response.getWriter().println(gson.toJson(result));

    }

    /**
     * 添加管理员账户
     * @param request
     * @param response
     */
    private void addAdmins(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String requestBody = HttpUtils.getRequestBody(request);
        Admin admin = gson.fromJson(requestBody, Admin.class);
        String message = adminService.addAdmins(admin);
        if("创建管理员成功！".equals(message)){
            result.setCode(0);
            result.setMessage(message);
        }else{
            result.setCode(10000);
            result.setMessage(message);
        }
        response.getWriter().println(gson.toJson(result));
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

        String requestBody = HttpUtils.getRequestBody(request);
        AdminLoginBO loginBO = gson.fromJson(requestBody, AdminLoginBO.class);
//        response.getWriter().println(loginBO);
        //该查询数据库，分层，新建service和dao
        //根据login的结果，返回不同的响应
        Admin login = adminService.login(loginBO);
//        Result result = new Result();
        if(login != null){
            AdminLoginVO loginVO = new AdminLoginVO();
            loginVO.setToken(login.getNickname());
            loginVO.setName(login.getNickname());
            response.getWriter().println(gson.toJson(Result.ok(loginVO)));
        }else{
            response.getWriter().println(gson.toJson(Result.error(",用户名或密码错误！")));
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //处理显示allAdmins
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/admin/", "");
        if("allAdmins".equals(action)){
            allAdmins(request,response);
        }else if("deleteAdmins".equals(action)){
            deleteAdmins(request, response);
        }else if("getAdminsInfo".equals(action)){
            getAdminsInfo(request, response);
        }
    }

    /**
     * 显示当前管理员的详细信息
     * @param request
     * @param response
     */
    private void getAdminsInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Integer id = Integer.parseInt(request.getParameter("id"));
        Admin admin = adminService.getAdminsInfo(id);
        result.setCode(0);
        result.setData(admin);
        response.getWriter().println(gson.toJson(result));

    }

    /**
     * 删除某个管理员账户
     * 1.获得id
     * 2.根据id在数据库中删除该账户
     * @param request
     * @param response
     */
    private void deleteAdmins(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Integer id = Integer.parseInt(request.getParameter("id"));
        adminService.deleteAdmins(id);
        result.setCode(0);
        result.setMessage("删除成功！");
        response.getWriter().println(gson.toJson(result));

    }

    /**
     * 显示所有的admin账户信息
     * 1.查询数据库，返回数据
     * 2.做出响应
     * @param request
     * @param response
     */
    private void allAdmins(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //根据响应报文格式，data中是一个List对象，存放返回的admin数据,adminList中存放所有admin的信息，即data中的数据
        List<Admin> adminList = adminService.allAdmins();
//        Result result = new Result();
        result.setCode(0);
        result.setData(adminList);

        //做出响应体
        response.getWriter().println(gson.toJson(result));

    }
}
