package com.mc.library_mc.dto;


import lombok.Data;
import java.math.BigDecimal;

@Data
public class BookQueryDTO {
    private String title;
    private String author;
    private String isbn;
    private Long categoryId;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private Integer pageNum = 1;
    private Integer pageSize = 10;
}