package com.example.bookmanagement.Services;

import com.example.bookmanagement.DataAccessObjects.BookOperations;
import com.example.bookmanagement.Models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {

    private final BookOperations databaseAccessObject;

    @Autowired
    public BookService(@Qualifier("cloudSQL") BookOperations databaseAccessObject) {
        this.databaseAccessObject = databaseAccessObject;
    }

//    public Iterable<Book> query(String keyword) {
//        return databaseAccessObject.query(keyword);
//    }

    public Iterable<Book> query() {
        return databaseAccessObject.findAll();
    }

    public Optional<Book> query(UUID uuid) {
        return databaseAccessObject.findById(uuid);
    }

    public Book add(Book book) {
        return databaseAccessObject.save(book);
    }

    public Book add(String title, String author, String publisher) {
        return databaseAccessObject.save(new Book(title, author, publisher));
    }

    public Book add(String title, String author, String publisher, Integer pagesCount) {
        return databaseAccessObject.save(new Book(title, author, publisher, pagesCount));
    }

    public void delete(UUID uuid) {
        databaseAccessObject.deleteById(uuid);
    }

//    public boolean update(Book newBook) {
//        return databaseAccessObject.update(newBook);
//    }
}
