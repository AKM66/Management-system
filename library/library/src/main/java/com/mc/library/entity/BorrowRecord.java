package com.mc.library.entity;


import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class BorrowRecord {
    private Long id;
    private Long bookId;
    private String bookTitle;
    private String borrower;
    private String phone;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private Integer status;
    private LocalDateTime createTime;
}