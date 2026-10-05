package com.mc.data;

import com.mc.display.Loginframe;
import com.mc.link.UserMapper;
import com.mc.link.UserService;
import com.mc.link.UserServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        // 启动 Spring Boot 应用
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);

        // 获取 UserService 实例
        UserService userService = context.getBean(UserService.class);

        // 启动登录界面
        new Loginframe(userService);
    }

    // 如果 UserServiceImpl 是一个 Spring Bean，可以在这里定义它
    @Bean
    public UserService userService(UserMapper userMapper) {
        return new UserServiceImpl(userMapper);
    }
}