package com.cskaoyan.project1.dao;

import com.cskaoyan.project1.model.User;

import java.util.List;

/**
 * 数据库相关操作
 * @auther Ningbo Tien
 * @date 2020-07-06 11:13
 */
public interface UserDao {

    //返回所有用户
    List<User> allUser();

    //删除指定用户
    void deleteUser(String id);

    //查找用户
    List<User> searchUser(String word);
}
