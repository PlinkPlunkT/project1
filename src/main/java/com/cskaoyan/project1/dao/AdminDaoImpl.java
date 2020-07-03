package com.cskaoyan.project1.dao;

import com.cskaoyan.project1.model.Admin;
import com.cskaoyan.project1.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * @auther Ningbo Tien
 * @date 2020-07-03 20:23
 */
public class AdminDaoImpl implements AdminDao {
    @Override
    public Admin login(Admin admin) {
        //数据库查询
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
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
}
