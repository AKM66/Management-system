package com.mc.library_mc.service1;


import com.baomidou.mybatisplus.extension.service.IService;
import com.mc.library_mc.entity.User;

public interface UserService extends IService<User> {
    User findByUsername(String username);
}