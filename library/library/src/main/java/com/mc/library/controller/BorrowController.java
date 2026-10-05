package com.mc.library.controller;


import com.mc.library.common.PageResult;
import com.mc.library.common.Result;
import com.mc.library.dto.BorrowDTO;
import com.mc.library.entity.BorrowRecord;
import com.mc.library.service.BorrowService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/borrows")
@RequiredArgsConstructor
public class BorrowController {

    private final BorrowService borrowService;

    @PostMapping
    public Result<Void> borrow(@Valid @RequestBody BorrowDTO dto) {
        borrowService.borrow(dto);
        return Result.success("借阅成功", null);
    }

    @PutMapping("/{id}/return")
    public Result<Void> returnBook(@PathVariable Long id) {
        borrowService.returnBook(id);
        return Result.success("归还成功", null);
    }

    @GetMapping
    public Result<PageResult<BorrowRecord>> page(
            @RequestParam(required = false) String borrower,
            @RequestParam(required = false) Integer status,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize
    ) {
        return Result.success(borrowService.page(borrower, status, pageNum, pageSize));
    }

    @GetMapping("/stats")
    public Result<Map<String, Long>> stats() {
        return Result.success(borrowService.stats());
    }
}