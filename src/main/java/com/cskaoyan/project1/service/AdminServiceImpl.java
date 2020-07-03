package com.cskaoyan.project1.service;

import com.cskaoyan.project1.dao.AdminDao;
import com.cskaoyan.project1.dao.AdminDaoImpl;
import com.cskaoyan.project1.model.Admin;
import com.cskaoyan.project1.model.bo.AdminLoginBO;

/**
 * @auther Ningbo Tien
 * @date 2020-07-03 20:00
 */
public class AdminServiceImpl implements AdminService {

    private AdminDao adminDao = new AdminDaoImpl();

    @Override
    public Admin login(AdminLoginBO loginBO) {
        Admin admin = new Admin();
        admin.setEmail(loginBO.getEmail());
        admin.setPwd(loginBO.getPwd());
        return adminDao.login(admin);
    }
}
