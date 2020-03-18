package com.example.bookmanagement.DataAccessObjects;

import com.example.bookmanagement.Models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Repository("cloudSQL")
public class CloudSQLBookRepository implements BookOperations {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CloudSQLBookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Book> query(String keyword) {
        return null;
    }

    @Override
    public List<Book> query() {
        return jdbcTemplate.query("SELECT * FROM Books",
                (row, rowNo) -> new Book(UUID.fromString(
                        row.getString("uuid")),
                        row.getString("title"),
                        row.getString("publisher"),
                        row.getInt("pages"),
                        row.getString("author")));
    }

    @Override
    public Book query(UUID uuid) {
        return jdbcTemplate.query(String.format("SELECT * FROM Books WHERE uuid='%s'", uuid.toString()),
                (row, rowNo) -> new Book(UUID.fromString(
                        row.getString("uuid")),
                        row.getString("title"),
                        row.getString("publisher"),
                        row.getInt("pages"),
                        row.getString("author")))
                .stream()
                .findFirst()
                .get();
    }

    @Override
    public boolean add(Book book) {
        try {
            jdbcTemplate.execute(String.format("INSERT INTO Books(uuid, title, author, publisher, pages) VALUES ('%s', '%s', '%s', '%s', %d)",
                    book.getUuid().toString(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getPublisher(),
                    book.getPagesCount()));
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    @Override
    public Book delete(UUID uuid) {
        try {
            jdbcTemplate.execute(String.format("DELETE FROM Books WHERE uuid='%s'",
                    uuid.toString()));
            return null;
        } catch (Exception exception) {
            return null;
        }
    }

    @Override
    public List<Book> delete(String title, String author, String publisher) {
        return null;
    }

    @Override
    public boolean update(Book newBook) {
        return false;
    }
}
