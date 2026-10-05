package com.mc.library.dto;


import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BorrowDTO {
    @NotNull(message = "图书ID不能为空")
    private Long bookId;

    @NotBlank(message = "借阅人不能为空")
    private String borrower;

    private String phone;

    @NotNull(message = "借阅日期不能为空")
    private LocalDate borrowDate;

    @NotNull(message = "应还日期不能为空")
    @FutureOrPresent(message = "应还日期不能早于当前日期")
    private LocalDate dueDate;
}