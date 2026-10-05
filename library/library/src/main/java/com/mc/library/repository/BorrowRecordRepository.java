package com.mc.library.repository;


import com.mc.library.entity.BorrowRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BorrowRecordRepository {

    private final JdbcTemplate jdbcTemplate;

    public int insert(BorrowRecord record) {
        String sql = """
                insert into borrow_record(book_id, book_title, borrower, phone, borrow_date, due_date, status)
                values(?, ?, ?, ?, ?, ?, ?)
                """;
        return jdbcTemplate.update(sql,
                record.getBookId(),
                record.getBookTitle(),
                record.getBorrower(),
                record.getPhone(),
                record.getBorrowDate(),
                record.getDueDate(),
                record.getStatus());
    }

    public BorrowRecord findById(Long id) {
        String sql = "select * from borrow_record where id = ?";
        List<BorrowRecord> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(BorrowRecord.class), id);
        return list.isEmpty() ? null : list.get(0);
    }

    public int markReturned(Long id, LocalDate returnDate) {
        String sql = """
                update borrow_record
                set status = 1, return_date = ?
                where id = ? and status = 0
                """;
        return jdbcTemplate.update(sql, returnDate, id);
    }

    public long count(String borrower, Integer status) {
        StringBuilder sql = new StringBuilder("select count(*) from borrow_record where 1=1 ");
        List<Object> params = new ArrayList<>();

        if (borrower != null && !borrower.trim().isEmpty()) {
            sql.append(" and borrower like ?");
            params.add("%" + borrower.trim() + "%");
        }
        if (status != null) {
            sql.append(" and status = ?");
            params.add(status);
        }

        return jdbcTemplate.queryForObject(sql.toString(), Long.class, params.toArray());
    }

    public List<BorrowRecord> findPage(String borrower, Integer status, int offset, int pageSize) {
        StringBuilder sql = new StringBuilder("select * from borrow_record where 1=1 ");
        List<Object> params = new ArrayList<>();

        if (borrower != null && !borrower.trim().isEmpty()) {
            sql.append(" and borrower like ?");
            params.add("%" + borrower.trim() + "%");
        }
        if (status != null) {
            sql.append(" and status = ?");
            params.add(status);
        }

        sql.append(" order by id desc limit ?, ?");
        params.add(offset);
        params.add(pageSize);

        return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(BorrowRecord.class), params.toArray());
    }

    public long countBorrowing() {
        return jdbcTemplate.queryForObject("select count(*) from borrow_record where status = 0", Long.class);
    }
}