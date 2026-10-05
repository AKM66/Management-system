package com.mc.view;

import com.mc.link.userlink;

public class StudentMain {
    //程序入口
    public static void main(String[] args) {
        /**
         * 打开主程序，连接数据库
         */
        userlink dbuser = new userlink("root", "root", "stu");
        loginview loginview = new loginview();//打开登录窗口


    }
}
