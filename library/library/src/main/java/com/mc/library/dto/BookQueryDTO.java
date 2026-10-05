package com.mc.library.dto;


import lombok.Data;

@Data
public class BookQueryDTO {
    private String keyword;
    private Integer status;
    private Integer pageNum = 1;
    private Integer pageSize = 10;
}