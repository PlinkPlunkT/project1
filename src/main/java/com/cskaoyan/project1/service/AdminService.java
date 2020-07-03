package com.cskaoyan.project1.service;

import com.cskaoyan.project1.model.Admin;
import com.cskaoyan.project1.model.bo.AdminLoginBO;

/**
 * @auther Ningbo Tien
 * @date 2020-07-03 19:58
 */
public interface AdminService {
    Admin login(AdminLoginBO loginBO);
}
