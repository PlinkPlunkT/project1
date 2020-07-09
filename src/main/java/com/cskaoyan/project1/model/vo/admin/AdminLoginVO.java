package com.cskaoyan.project1.model.vo.admin;

/**
 * @auther Ningbo Tien
 * @date 2020-07-03 21:20
 * 管理员登陆成功返回vo
 */
public class AdminLoginVO {
    private String token;

    private String name;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
