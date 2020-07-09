package com.cskaoyan.project1.service;

import com.cskaoyan.project1.model.Admin;
import com.cskaoyan.project1.model.bo.admin.AdminChangePwdBO;
import com.cskaoyan.project1.model.bo.admin.AdminLoginBO;

import java.util.List;

/**
 * 与管理员有关的方法
 * @auther Ningbo Tien
 * @date 2020-07-03 19:58
 */
public interface AdminService {

    String addAdmins(Admin admin);

    //登录
    Admin login(AdminLoginBO loginBO);

    //显示全部管理员
    List<Admin> allAdmins();


    //删除管理员账户
    void deleteAdmins(Integer id);

    //显示账户信息
    Admin getAdminsInfo(Integer id);

    //更新账户信息
    String updateAdminss(Admin admin);
    //得到当前用户的信息
    Admin getCurrentAdmin(Admin admin);

    //查找admin
    List<Admin> getSearchAdmins(Admin admin);

    //更改当前管理员密码
    String changePwd(AdminChangePwdBO adminChangePwdBO);
}
