package com.mc.library_mc.service1;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mc.library_mc.dto.BookQueryDTO;
import com.mc.library_mc.entity.Book;

public interface BookService extends IService<Book> {
    Page<Book> queryBooks(BookQueryDTO queryDTO);
}