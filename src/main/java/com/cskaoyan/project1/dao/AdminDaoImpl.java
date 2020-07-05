package com.cskaoyan.project1.dao;

import com.cskaoyan.project1.model.Admin;
import com.cskaoyan.project1.model.bo.AdminChangePwdBO;
import com.cskaoyan.project1.utils.DruidUtils;
import com.mysql.jdbc.StringUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther Ningbo Tien
 * @date 2020-07-03 20:23
 */
public class AdminDaoImpl implements AdminDao {

    private QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());

    @Override
    public int changePwd(AdminChangePwdBO adminChangePwdBO) {

        int code = 0;
        try {
            code = runner.update("update admin set pwd = ? where nickname = ? and pwd = ?",
                    adminChangePwdBO.getNewPwd(), adminChangePwdBO.getAdminToken(), adminChangePwdBO.getOldPwd());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return code;
    }

    @Override
    public List<Admin> getSearchAdmins(Admin admin) {
        Map<String, Object> result = getDynamicSql(admin);
        String sql = (String)result.get("sql");
        List<String> params = (List<String>)result.get("params");
        List<Admin> admins = new ArrayList<>();

        try {
            admins = runner.query(sql, new BeanListHandler<Admin>(Admin.class), params.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admins;

    }

    private Map<String, Object> getDynamicSql(Admin admin) {

        Map<String, Object> sqlMap = new HashMap<>();
        List<String> params = new ArrayList<>();
        String sql = "select * from admin where 1 = 1";

        if(!StringUtils.isNullOrEmpty(admin.getEmail())){
            sql = sql + " and email like ?";
            params.add("%" + admin.getEmail() + "%");
        }
        if(!StringUtils.isNullOrEmpty(admin.getNickname())){
            sql = sql + " and nickname like ?";
            params.add("%" + admin.getNickname() + "%");
        }

        sqlMap.put("sql", sql);
        sqlMap.put("params",params);
        return sqlMap;
    }

    @Override
    public Admin getCurrentAdmin(Integer id) {
        Admin admin = null;
        try {
            admin = runner.query("select * from admin where id = ?",new BeanHandler<>(Admin.class), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }

    @Override
    public void updateAdminss(Admin admin) {
        try {
            runner.execute("update admin set email = ?, pwd = ?, nickname = ? where id = ?",
                    admin.getEmail(), admin.getPwd(), admin.getNickname(), admin.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Admin getAdminsInfo(Integer id) {
        Admin admin = null;
        try {
            admin = runner.query("select * from admin where id = ?", new BeanHandler<>(Admin.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return admin;
    }

    @Override
    public void deleteAdmins(Integer id) {
        try {
            runner.execute("delete from admin where id = ?", id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isNicknameExists(String nickname) {

        Admin admin = null;
        try {
            admin = runner.query("select * from admin where binary nickname = ?", new BeanHandler<>(Admin.class), nickname);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(admin == null){
            return false;
        }
        return true;
    }

    @Override
    public boolean isEmailExists(String email) {

        Admin admin = null;
        try {
            admin = runner.query("select * from admin where email = ?", new BeanHandler<>(Admin.class), email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(admin == null){
            return false;
        }
        return true;
    }

    @Override
    public void addAdmins(Admin admin) {
        try {
            runner.update("insert into admin values(null, ?, ?, ?)", admin.getEmail(),
                    admin.getPwd(), admin.getNickname());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Admin login(Admin admin) {
        //数据库查询
//        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Admin query = null;
        try {
            query = runner.query("select * from admin where email = ? and pwd = ?",
                    new BeanHandler<>(Admin.class),
                    admin.getEmail(),
                    admin.getPwd());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    @Override
    public List<Admin> allAdmins() {
        //查询数据库中的所有admin信息
//        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<Admin> admins = null;
        try {

            admins = runner.query("select * from admin", new BeanListHandler<Admin>(Admin.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return admins;

    }
}
