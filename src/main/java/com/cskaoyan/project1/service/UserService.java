package com.cskaoyan.project1.service;

import com.cskaoyan.project1.dao.UserDao;
import com.cskaoyan.project1.model.User;

import java.util.List;

/**
 * 用户的service方法
 * @auther Ningbo Tien
 * @date 2020-07-06 11:08
 */
public interface UserService {

    //显示全部用户
    List<User> allUser();

    //删除指定用户
    String deleteUser(String id);

    //查找用户
    List<User> searchUser(String word);
}
