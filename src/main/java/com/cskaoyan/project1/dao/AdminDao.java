package com.cskaoyan.project1.dao;

import com.cskaoyan.project1.model.Admin;
import com.cskaoyan.project1.model.bo.admin.AdminChangePwdBO;

import java.util.List;

/**
 * @auther Ningbo Tien
 * @date 2020-07-03 20:22
 */
public interface AdminDao {

    Admin getCurrentAdmin(Integer id);

    Admin login(Admin admin);

    List<Admin> allAdmins();

    //在数据库中添加管理员账户
    void addAdmins(Admin admin);

    //是否已存在该昵称
    boolean isNicknameExists(String nickname);

    //该邮箱是否已存在
    boolean isEmailExists(String email);

    //在数据库中删除某个管理员账户
    void deleteAdmins(Integer id);

    //从数据库中取出管理员信息
    Admin getAdminsInfo(Integer id);

    //更新数据库中管理员信息
    void updateAdminss(Admin admin);

    //从数据库中查找admin
    List<Admin> getSearchAdmins(Admin admin);

    //更改当前管理员密码
    int changePwd(AdminChangePwdBO adminChangePwdBO);
}
