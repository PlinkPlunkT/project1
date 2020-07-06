package com.cskaoyan.project1.dao;

import com.cskaoyan.project1.model.User;
import com.cskaoyan.project1.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther Ningbo Tien
 * @date 2020-07-06 11:20
 */
public class UserDaoImpl implements UserDao {

    private QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());

    @Override
    public List<User> searchUser(String word) {

        List<User> users = new ArrayList<User>();
        try {
            users = runner.query("select * from user where 1=1 and nickname like ?",
                    new BeanListHandler<User>(User.class), "%" + word +"%");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void deleteUser(String id) {
        try {
            runner.execute("delete from user where id = ?", id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> allUser() {

        List<User> users = null;
        try {
            users = runner.query("select * from user", new BeanListHandler<User>(User.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
}
