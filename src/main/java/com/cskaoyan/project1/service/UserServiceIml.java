package com.cskaoyan.project1.service;

import com.cskaoyan.project1.dao.UserDao;
import com.cskaoyan.project1.dao.UserDaoImpl;
import com.cskaoyan.project1.model.User;

import java.util.List;

/**
 * 使用UserService接口的类
 * @auther Ningbo Tien
 * @date 2020-07-06 11:09
 */
public class UserServiceIml implements UserService {

    UserDao userDao = new UserDaoImpl();

    @Override
    public List<User> searchUser(String word) {
        return userDao.searchUser(word);
    }

    @Override
    public String deleteUser(String id) {
        userDao.deleteUser(id);
        return "删除成功！";
    }

    @Override
    public List<User> allUser() {
        return userDao.allUser();
    }
}
