package com.mc.library_mc.vo;


import lombok.Data;

@Data
public class LoginVO {
    private String token;
    private String username;
    private String role;
}