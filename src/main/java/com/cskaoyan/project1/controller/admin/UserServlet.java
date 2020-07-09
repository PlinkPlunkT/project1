package com.cskaoyan.project1.controller.admin;

import com.cskaoyan.project1.model.Result;
import com.cskaoyan.project1.model.User;
import com.cskaoyan.project1.service.UserService;
import com.cskaoyan.project1.service.UserServiceIml;
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
 * 用户相关接口实现
 * @auther Ningbo Tien
 * @date 2020-07-06 11:02
 */
@WebServlet("/api/admin/user/*")
public class UserServlet extends HttpServlet {

    private UserService userService = new UserServiceIml();
    private Result result = new Result();
    private Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/user/", "");
        if("allUser".equals(action)){
            allUser(request, response);
        }else if("deleteUser".equals(action)){
            deleteUser(request, response);
        }else if("searchUser".equals(action)){
            searchUser(request, response);
        }

    }

    /**
     * 查找用户
     * @param request
     * @param response
     */
    private void searchUser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String word = request.getParameter("word");
        List<User> userList = userService.searchUser(word);
        result.setCode(0);
        result.setData(userList);
        response.getWriter().println(gson.toJson(result));
    }

    /**
     * 删除某个用户
     * @param request
     * @param response
     * @throws IOException
     */
    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String id = request.getParameter("id");
        String message = userService.deleteUser(id);
        result.setCode(0);
        result.setMessage(message);
        response.getWriter().println(gson.toJson(result));
    }

    /**
     * 显示所有用户信息
     * @param request
     * @param response
     */
    private void allUser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //返回的数据用一个list存放
        List<User> userList = userService.allUser();
        result.setCode(0);
        result.setData(userList);
        response.getWriter().println(gson.toJson(result));
    }
}
