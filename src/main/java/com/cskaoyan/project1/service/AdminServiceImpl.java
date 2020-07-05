package com.cskaoyan.project1.service;

import com.cskaoyan.project1.dao.AdminDao;
import com.cskaoyan.project1.dao.AdminDaoImpl;
import com.cskaoyan.project1.model.Admin;
import com.cskaoyan.project1.model.bo.AdminChangePwdBO;
import com.cskaoyan.project1.model.bo.AdminLoginBO;

import java.util.List;

/**
 * @auther Ningbo Tien
 * @date 2020-07-03 20:00
 */
public class AdminServiceImpl implements AdminService {

    private AdminDao adminDao = new AdminDaoImpl();

    @Override
    public Admin getCurrentAdmin(Admin admin) {
        return adminDao.getCurrentAdmin(admin.getId());
    }

    @Override
    public String changePwd(AdminChangePwdBO adminChangePwdBO) {

        if(adminDao.changePwd(adminChangePwdBO) != 1) return "旧密码错误！";
        if(!confirmPwd(adminChangePwdBO)) return "两次输入的密码不一致!";

        return "修改成功！";
    }

    /**
     * 确认更改密码时两次输入的密码是否一致
     * @param adminChangePwdBO
     * @return
     */
    private boolean confirmPwd(AdminChangePwdBO adminChangePwdBO) {
        return adminChangePwdBO.getNewPwd().equals(adminChangePwdBO.getConfirmPwd());
    }

    @Override
    public List<Admin> getSearchAdmins(Admin admin) {
        return adminDao.getSearchAdmins(admin);
    }

    @Override
    public String updateAdminss(Admin admin) {
        //先判断更改后的邮箱与原邮箱是否相同
        AdminService service = new AdminServiceImpl();
        Admin oldAdmin = service.getCurrentAdmin(admin);
        if(!oldAdmin.getEmail().equals(admin.getEmail())){
            //新旧email不同
            //判断新的email是否已存在
            if(adminDao.isEmailExists(admin.getEmail())) return "不允许使用重复的邮箱！";
        }
        if(!oldAdmin.getNickname().equals(admin.getNickname())){
            //新旧昵称不同，判断新的昵称是否已存在
            if(adminDao.isNicknameExists(admin.getNickname())) return "不允许使用重复的昵称！";
        }
        adminDao.updateAdminss(admin);
        return " ";
    }

    @Override
    public Admin getAdminsInfo(Integer id) {
        return adminDao.getAdminsInfo(id);
    }

    @Override
    public void deleteAdmins(Integer id) {
        adminDao.deleteAdmins(id);
    }

    @Override
    public String addAdmins(Admin admin) {

        AdminDao dao = new AdminDaoImpl();
        if(dao.isEmailExists(admin.getEmail())) return "创建失败！此邮箱已被使用！";
        if(dao.isNicknameExists(admin.getNickname())) return "创建失败！此昵称已被使用！";
        dao.addAdmins(admin);
        return "创建管理员成功！";
    }

    @Override
    public Admin login(AdminLoginBO loginBO) {
        Admin admin = new Admin();
        admin.setEmail(loginBO.getEmail());
        admin.setPwd(loginBO.getPwd());
        return adminDao.login(admin);
    }

    @Override
    public List<Admin> allAdmins() {
        return adminDao.allAdmins();
    }
}
