package com.mc.link;

import java.sql.*;

public class userlink {


    private String account;
    private String password;
    private String db;

    /**
     * 账号，数据库密码，数据库名称
     */
    public userlink(String account, String password, String db) {
        this.account = account;
        this.password = password;
        this.db = db;
        //z实现加载数据库的驱动，以及创建数据库连接
        init();
    }

    public static Connection con =null;
    private void init() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("加载成功");
        } catch (Exception e) {
            System.out.println("加载失败");
        }
        ///连接数据库
        try {
            String url = "jdbc:mysql://127.0.0.1:3306/mc01?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";
            con= DriverManager.getConnection(url,account,password);
            System.out.println("数据库连接成功");
        } catch (Exception e) {
            System.out.println("数据库连接失败");
        }
    }

}
