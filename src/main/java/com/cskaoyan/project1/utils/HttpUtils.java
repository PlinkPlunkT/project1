package com.cskaoyan.project1.utils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 将请求体中数据变成json字符串
 * @auther Ningbo Tien
 * @date 2020-07-05 11:46
 */
public class HttpUtils {
    public static String getRequestBody(HttpServletRequest request) throws IOException {
        //取出请求参数，取出请求体里面的数据
        //此时无法使用request.getParameter key=value&key1=value,手动解析
        ServletInputStream inputStream = request.getInputStream();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int length = 0;
        while((length = inputStream.read(bytes)) != -1){
            outputStream.write(bytes, 0, length);
        }

        //返回请求体中的数据
        return outputStream.toString("utf-8");
    }
}
