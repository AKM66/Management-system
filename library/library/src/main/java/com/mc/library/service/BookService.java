package com.mc.library.service;


import com.mc.library.common.PageResult;
import com.mc.library.dto.BookQueryDTO;
import com.mc.library.entity.Book;
import com.mc.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public PageResult<Book> page(BookQueryDTO dto) {
        int pageNum = dto.getPageNum() == null || dto.getPageNum() < 1 ? 1 : dto.getPageNum();
        int pageSize = dto.getPageSize() == null || dto.getPageSize() < 1 ? 10 : dto.getPageSize();
        int offset = (pageNum - 1) * pageSize;

        long total = bookRepository.count(dto.getKeyword(), dto.getStatus());
        return new PageResult<>(
                total,
                bookRepository.findPage(dto.getKeyword(), dto.getStatus(), offset, pageSize)
        );
    }

    public void add(Book book) {
        if (book.getStock() == null || book.getStock() < 0) {
            throw new RuntimeException("库存不能小于0");
        }
        if (book.getAvailable() == null) {
            book.setAvailable(book.getStock());
        }
        if (book.getAvailable() > book.getStock()) {
            throw new RuntimeException("可借数量不能大于总库存");
        }
        if (book.getStatus() == null) {
            book.setStatus(1);
        }
        bookRepository.insert(book);
    }

    public void update(Book book) {
        Book old = bookRepository.findById(book.getId());
        if (old == null) {
            throw new RuntimeException("图书不存在");
        }
        if (book.getStock() < 0 || book.getAvailable() < 0) {
            throw new RuntimeException("库存不能小于0");
        }
        if (book.getAvailable() > book.getStock()) {
            throw new RuntimeException("可借数量不能大于总库存");
        }
        bookRepository.update(book);
    }

    public void delete(Long id) {
        Book old = bookRepository.findById(id);
        if (old == null) {
            throw new RuntimeException("图书不存在");
        }
        if (old.getStock() > old.getAvailable()) {
            throw new RuntimeException("该图书还有借出记录，不能删除");
        }
        bookRepository.deleteById(id);
    }

    public Map<String, Long> stats() {
        Map<String, Long> map = new HashMap<>();
        map.put("bookCount", bookRepository.countBooks());
        map.put("availableCount", bookRepository.countAvailableBooks());
        map.put("borrowedCount", bookRepository.countBorrowedBooks());
        return map;
    }
}