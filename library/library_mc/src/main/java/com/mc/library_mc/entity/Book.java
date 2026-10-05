package com.mc.library_mc.entity;


import com.baomidou.mybatisplus.annotation.*;
        import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("book")
public class Book {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private LocalDateTime publishDate;
    private BigDecimal price;
    private Integer stock;
    private Integer total;
    private Long categoryId;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}