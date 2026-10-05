package com.mc.library.controller;


import com.mc.library.common.PageResult;
import com.mc.library.common.Result;
import com.mc.library.dto.BookQueryDTO;
import com.mc.library.entity.Book;
import com.mc.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public Result<PageResult<Book>> page(BookQueryDTO dto) {
        return Result.success(bookService.page(dto));
    }

    @PostMapping
    public Result<Void> add(@RequestBody Book book) {
        bookService.add(book);
        return Result.success("新增成功", null);
    }

    @PutMapping("/{id}")
    public Result<Void> update(@PathVariable Long id, @RequestBody Book book) {
        book.setId(id);
        bookService.update(book);
        return Result.success("修改成功", null);
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        bookService.delete(id);
        return Result.success("删除成功", null);
    }

    @GetMapping("/stats")
    public Result<Map<String, Long>> stats() {
        return Result.success(bookService.stats());
    }
}