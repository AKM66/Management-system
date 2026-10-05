package com.mc.library.repository;


import com.mc.library.entity.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookRepository {

    private final JdbcTemplate jdbcTemplate;

    public long count(String keyword, Integer status) {
        StringBuilder sql = new StringBuilder("select count(*) from book where 1=1 ");
        List<Object> params = new ArrayList<>();

        if (keyword != null && !keyword.trim().isEmpty()) {
            sql.append(" and (title like ? or author like ? or isbn like ?)");
            String like = "%" + keyword.trim() + "%";
            params.add(like);
            params.add(like);
            params.add(like);
        }
        if (status != null) {
            sql.append(" and status = ?");
            params.add(status);
        }

        return jdbcTemplate.queryForObject(sql.toString(), Long.class, params.toArray());
    }

    public List<Book> findPage(String keyword, Integer status, int offset, int pageSize) {
        StringBuilder sql = new StringBuilder("select * from book where 1=1 ");
        List<Object> params = new ArrayList<>();

        if (keyword != null && !keyword.trim().isEmpty()) {
            sql.append(" and (title like ? or author like ? or isbn like ?)");
            String like = "%" + keyword.trim() + "%";
            params.add(like);
            params.add(like);
            params.add(like);
        }
        if (status != null) {
            sql.append(" and status = ?");
            params.add(status);
        }

        sql.append(" order by id desc limit ?, ?");
        params.add(offset);
        params.add(pageSize);

        return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(Book.class), params.toArray());
    }

    public Book findById(Long id) {
        String sql = "select * from book where id = ?";
        List<Book> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class), id);
        return list.isEmpty() ? null : list.get(0);
    }

    public int insert(Book book) {
        String sql = """
                insert into book(title, author, publisher, isbn, category, stock, available, status)
                values(?, ?, ?, ?, ?, ?, ?, ?)
                """;
        return jdbcTemplate.update(sql,
                book.getTitle(),
                book.getAuthor(),
                book.getPublisher(),
                book.getIsbn(),
                book.getCategory(),
                book.getStock(),
                book.getAvailable(),
                book.getStatus());
    }

    public int update(Book book) {
        String sql = """
                update book
                set title = ?, author = ?, publisher = ?, isbn = ?, category = ?, stock = ?, available = ?, status = ?
                where id = ?
                """;
        return jdbcTemplate.update(sql,
                book.getTitle(),
                book.getAuthor(),
                book.getPublisher(),
                book.getIsbn(),
                book.getCategory(),
                book.getStock(),
                book.getAvailable(),
                book.getStatus(),
                book.getId());
    }

    public int deleteById(Long id) {
        String sql = "delete from book where id = ?";
        return jdbcTemplate.update(sql, id);
    }

    public int decreaseAvailable(Long id) {
        String sql = "update book set available = available - 1 where id = ? and available > 0";
        return jdbcTemplate.update(sql, id);
    }

    public int increaseAvailable(Long id) {
        String sql = "update book set available = available + 1 where id = ? and available < stock";
        return jdbcTemplate.update(sql, id);
    }

    public long countBooks() {
        return jdbcTemplate.queryForObject("select count(*) from book", Long.class);
    }

    public long countAvailableBooks() {
        return jdbcTemplate.queryForObject("select ifnull(sum(available), 0) from book", Long.class);
    }

    public long countBorrowedBooks() {
        return jdbcTemplate.queryForObject("select ifnull(sum(stock - available), 0) from book", Long.class);
    }
}