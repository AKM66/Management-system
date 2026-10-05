package com.mc.library.entity;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Book {
    private Long id;
    private String title;
    private String author;
    private String publisher;
    private String isbn;
    private String category;
    private Integer stock;
    private Integer available;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}