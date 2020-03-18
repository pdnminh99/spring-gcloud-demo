package com.example.bookmanagement.DataAccessObjects;

import com.example.bookmanagement.Models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository("cloudSQL")
public class CloudSQLBookRepository implements BookOperations {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CloudSQLBookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//    public Iterable<Book> query(String keyword) {
//        return null;
//    }

//    @Override
//    public boolean update(Book newBook) {
//        return false;
//    }

    @Override
    public <S extends Book> S save(S book) {
        try {
            jdbcTemplate.execute(String.format("INSERT INTO Books(uuid, title, author, publisher, pages) VALUES ('%s', '%s', '%s', '%s', %d)",
                    book.getUuid().toString(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getPublisher(),
                    book.getPagesCount()));
            return book;
        } catch (Exception exception) {
            return null;
        }
    }

    @Override
    public <S extends Book> Iterable<S> saveAll(Iterable<S> iterable) {
        try {
            iterable.forEach(this::save);
            return iterable;
        } catch (Exception exception) {
            return null;
        }
    }

    @Override
    public Optional<Book> findById(UUID uuid) {
        return jdbcTemplate.query(String.format("SELECT * FROM Books WHERE uuid='%s'", uuid.toString()),
                (row, rowNo) -> new Book(UUID.fromString(
                        row.getString("uuid")),
                        row.getString("title"),
                        row.getString("publisher"),
                        row.getInt("pages"),
                        row.getString("author")))
                .stream()
                .findFirst();
    }

    @Override
    public boolean existsById(UUID uuid) {
        return jdbcTemplate.query(String.format("SELECT * FROM Books WHERE uuid='%s'", uuid.toString()),
                (row, rowNo) -> new Book(UUID.fromString(
                        row.getString("uuid")),
                        row.getString("title"),
                        row.getString("publisher"),
                        row.getInt("pages"),
                        row.getString("author")))
                .size() > 0;
    }

    @Override
    public Iterable<Book> findAll() {
        return jdbcTemplate.query("SELECT * FROM Books",
                (row, rowNo) -> new Book(UUID.fromString(
                        row.getString("uuid")),
                        row.getString("title"),
                        row.getString("publisher"),
                        row.getInt("pages"),
                        row.getString("author")));
    }

    @Override
    public Iterable<Book> findAllById(Iterable<UUID> iterable) {
        return null;
    }

    @Override
    public long count() {
        return jdbcTemplate.query("SELECT * FROM Books",
                (row, rowNo) -> new Book(UUID.fromString(
                        row.getString("uuid")),
                        row.getString("title"),
                        row.getString("publisher"),
                        row.getInt("pages"),
                        row.getString("author")))
                .size();
    }

    @Override
    public void deleteById(UUID uuid) {
        try {
            jdbcTemplate.execute(String.format("DELETE FROM Books WHERE uuid='%s'",
                    uuid.toString()));
        } catch (Exception ignored) {
        }
    }

    @Override
    public void delete(Book book) {
        deleteById(book.getUuid());
    }

    @Override
    public void deleteAll(Iterable<? extends Book> iterable) {

    }

    @Override
    public void deleteAll() {
        try {
            jdbcTemplate.execute("DELETE FROM Books");
        } catch (Exception ignored) {
        }
    }
}
