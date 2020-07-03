package com.cskaoyan.project1.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @auther Ningbo Tien
 * @date 2020-07-03 20:45
 */
public class DruidUtils {

    private static DataSource dataSource;

    static{
        ////要用类加载器的一个API来获取绝对路径，可以和servletContext解耦
        InputStream inputStream = DruidUtils.class.getClassLoader().getResourceAsStream("druid.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  static DataSource getDataSource(){
        return dataSource;
    }

    public static Connection getConnection() throws SQLException{
        return dataSource.getConnection();
    }


}
