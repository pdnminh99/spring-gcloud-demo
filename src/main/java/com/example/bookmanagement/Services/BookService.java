package com.example.bookmanagement.Services;

import com.example.bookmanagement.DataAccessObjects.BookOperations;
import com.example.bookmanagement.Models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookService implements BookOperations {

    private final BookOperations databaseAccessObject;

    @Autowired
    public BookService(@Qualifier("cloudSQL") BookOperations databaseAccessObject) {
        this.databaseAccessObject = databaseAccessObject;
    }

    @Override
    public List<Book> query(String keyword) {
        return databaseAccessObject.query(keyword);
    }

    @Override
    public List<Book> query() {
        return databaseAccessObject.query();
    }

    @Override
    public Book query(UUID uuid) {
        return databaseAccessObject.query(uuid);
    }

    @Override
    public List<Book> query(UUID uuid, String title, String author, String publisher, Integer pageCount) {
        return databaseAccessObject.query(uuid, title, author, publisher, pageCount);
    }

    @Override
    public boolean add(Book book) {
        return databaseAccessObject.add(book);
    }

    @Override
    public Book delete(UUID uuid) {
        return databaseAccessObject.delete(uuid);
    }

    @Override
    public List<Book> delete(String title, String author, String publisher) {
        return databaseAccessObject.delete(title, author, publisher);
    }

    @Override
    public boolean update(Book newBook) {
        return databaseAccessObject.update(newBook);
    }
}
