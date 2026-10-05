package com.mc.library.service;


import com.mc.library.common.PageResult;
import com.mc.library.dto.BorrowDTO;
import com.mc.library.entity.Book;
import com.mc.library.entity.BorrowRecord;
import com.mc.library.repository.BookRepository;
import com.mc.library.repository.BorrowRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BorrowService {

    private final BorrowRecordRepository borrowRecordRepository;
    private final BookRepository bookRepository;
    private final JdbcTemplate jdbcTemplate;

    @Transactional
    public void borrow(BorrowDTO dto) {
        Book book = bookRepository.findById(dto.getBookId());
        if (book == null) {
            throw new RuntimeException("图书不存在");
        }
        if (book.getStatus() == 0) {
            throw new RuntimeException("该图书已下架，不能借阅");
        }
        if (book.getAvailable() <= 0) {
            throw new RuntimeException("库存不足");
        }
        if (dto.getDueDate().isBefore(dto.getBorrowDate())) {
            throw new RuntimeException("应还日期不能早于借阅日期");
        }

        int affected = bookRepository.decreaseAvailable(dto.getBookId());
        if (affected <= 0) {
            throw new RuntimeException("借阅失败，库存不足");
        }

        BorrowRecord record = new BorrowRecord();
        record.setBookId(book.getId());
        record.setBookTitle(book.getTitle());
        record.setBorrower(dto.getBorrower());
        record.setPhone(dto.getPhone());
        record.setBorrowDate(dto.getBorrowDate());
        record.setDueDate(dto.getDueDate());
        record.setStatus(0);

        borrowRecordRepository.insert(record);
    }

    @Transactional
    public void returnBook(Long recordId) {
        BorrowRecord record = borrowRecordRepository.findById(recordId);
        if (record == null) {
            throw new RuntimeException("借阅记录不存在");
        }
        if (record.getStatus() == 1) {
            throw new RuntimeException("该记录已归还");
        }

        int affected = borrowRecordRepository.markReturned(recordId, LocalDate.now());
        if (affected <= 0) {
            throw new RuntimeException("归还失败");
        }

        bookRepository.increaseAvailable(record.getBookId());
    }

    public PageResult<BorrowRecord> page(String borrower, Integer status, Integer pageNum, Integer pageSize) {
        int p = pageNum == null || pageNum < 1 ? 1 : pageNum;
        int s = pageSize == null || pageSize < 1 ? 10 : pageSize;
        int offset = (p - 1) * s;

        long total = borrowRecordRepository.count(borrower, status);
        return new PageResult<>(
                total,
                borrowRecordRepository.findPage(borrower, status, offset, s)
        );
    }

    public Map<String, Long> stats() {
        Map<String, Long> map = new HashMap<>();
        Long borrowingCount = borrowRecordRepository.countBorrowing();
        map.put("borrowingCount", borrowingCount);
        return map;
    }
}