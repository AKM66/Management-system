package com.mc.library_mc.controller;


import com.mc.library_mc.dto.LoginDTO;
import com.mc.library_mc.entity.User;
import com.mc.library_mc.service1.UserService;
import com.mc.library_mc.utils.JwtUtil;
import com.mc.library_mc.utils.PasswordUtil;
import com.mc.library_mc.vo.LoginVO;
import com.mc.library_mc.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordUtil passwordUtil;

    @PostMapping("/login")
    public Result<LoginVO> login(@Validated @RequestBody LoginDTO loginDTO) {
        User user = userService.findByUsername(loginDTO.getUsername());
        if (user == null || !passwordUtil.matches(loginDTO.getPassword(), user.getPassword())) {
            return Result.error(401, "用户名或密码错误");
        }

        String token = jwtUtil.generateToken(user.getUsername());
        LoginVO loginVO = new LoginVO();
        loginVO.setToken(token);
        loginVO.setUsername(user.getUsername());
        loginVO.setRole(user.getRole());

        return Result.success(loginVO);
    }

    @PostMapping("/register")
    public Result<Void> register(@Validated @RequestBody User user) {
        if (userService.findByUsername(user.getUsername()) != null) {
            return Result.error(400, "用户名已存在");
        }

        user.setPassword(passwordUtil.encode(user.getPassword()));
        user.setRole("USER");
        userService.save(user);

        return Result.success();
    }
}