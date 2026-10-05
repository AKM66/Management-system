package com.mc.library_mc.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mc.library_mc.dto.BookQueryDTO;
import com.mc.library_mc.entity.Book;
import com.mc.library_mc.service1.BookService;
import com.mc.library_mc.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public Result<Page<Book>> getBooks(BookQueryDTO queryDTO) {
        Page<Book> page = bookService.queryBooks(queryDTO);
        return Result.success(page);
    }

    @GetMapping("/{id}")
    public Result<Book> getBookById(@PathVariable Long id) {
        Book book = bookService.getById(id);
        return Result.success(book);
    }

    @PostMapping
    public Result<Void> addBook(@RequestBody Book book) {
        bookService.save(book);
        return Result.success();
    }

    @PutMapping("/{id}")
    public Result<Void> updateBook(@PathVariable Long id, @RequestBody Book book) {
        book.setId(id);
        bookService.updateById(book);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> deleteBook(@PathVariable Long id) {
        bookService.removeById(id);
        return Result.success();
    }
}