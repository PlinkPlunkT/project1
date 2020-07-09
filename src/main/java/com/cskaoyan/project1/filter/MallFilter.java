package com.cskaoyan.project1.filter;

import com.cskaoyan.project1.model.Admin;
import com.cskaoyan.project1.model.Result;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @auther Ningbo Tien
 * @date 2020-07-09 20:57
 */
@WebFilter("/api/mall/*")
public class MallFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //*改为页面系统所在的域
        response.setHeader("Access-Control-Allow-Origin","http://localhost:8085");
        response.setHeader("Access-Control-Allow-Methods","POST,GET,OPTIONS,PUT,DELETE");
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,Authorization,Content-Type");
        response.setHeader("Access-Control-Allow-Credentials","true");

        String requestURI = request.getRequestURI();

//        //1.那些需要拦截，哪些不需要拦截
//        //2.针对需要拦截的，判断有无session数据
//        //3.如果没有，则拦截
//        if(!request.getMethod().equals("OPTIONS")){
//            if(auth(requestURI)){
//                Admin admin = (Admin) request.getSession().getAttribute("admin");
//                if(admin == null){
//                    response.getWriter().println(new Gson().toJson(Result.error("当前接口仅可登陆后使用！")));
//                    return;
//                }
//            }
//        }

        chain.doFilter(request, response);
    }

    /**
     * 当前请求uri是否需要权限
     * @param requestURI
     * @return
     */
    private boolean auth(String requestURI) {
        if("/api/admin/admin/login".equals(requestURI) ||
                "/api/admin/admin/logoutAdmin".equals(requestURI)){
            return false;
        }
        return true;
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
