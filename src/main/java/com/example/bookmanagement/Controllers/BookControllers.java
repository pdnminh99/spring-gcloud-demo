package com.example.bookmanagement.Controllers;

import com.example.bookmanagement.DataAccessObjects.BookOperations;
import com.example.bookmanagement.Models.Book;
import com.example.bookmanagement.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/book")
public class BookControllers implements BookOperations {

    private final BookService service;

    @Autowired
    public BookControllers(BookService service) {
        this.service = service;
    }

    @GetMapping
    public ArrayList<Book> query() {
        return (ArrayList<Book>) service.query();
    }

    @GetMapping(path = "keyword/{keyword}")
    public List<Book> query(@PathVariable("keyword") String keyword) {
        return service.query(keyword);
    }

    @GetMapping(path = "{uuid}")
    public Book query(@PathVariable("uuid") UUID uuid) {
        return service.query(uuid);
    }

    @GetMapping(path = "{title}/{author}/{publisher}")
    public boolean add(@PathVariable("title") String title,
                       @PathVariable("author") String author,
                       @PathVariable("publisher") String publisher) {
        return service.add(title, author, publisher);
    }

    @GetMapping(path = "{title}/{author}/{publisher}/{pagesCount}")
    public boolean add(@PathVariable("title") String title,
                       @PathVariable("author") String author,
                       @PathVariable("publisher") String publisher,
                       @PathVariable("pagesCount") Integer pagesCount) {
        return service.add(title, author, publisher, pagesCount);
    }

    @PostMapping
    public boolean add(@Valid @NotNull @RequestBody Book book) {
        return service.add(book);
    }

    @DeleteMapping(path = "{uuid}")
    public Book delete(@PathVariable("uuid") UUID uuid) {
        return service.delete(uuid);
    }

    @Override
    public List<Book> delete(String title, String author, String publisher) {
        return null;
    }

    @DeleteMapping(path = "title/{title}")
    public List<Book> delete(@PathVariable("title") String title) {
        return service.delete(title, null, null);
    }

    @DeleteMapping(path = "author/{author}")
    public List<Book> deleteByAuthor(String author) {
        return service.delete(null, author, null);
    }

    @DeleteMapping(path = "publisher/{publisher}")
    public List<Book> deleteByPublisher(String publisher) {
        return service.delete(null, null, publisher);
    }

    @PutMapping
    public boolean update(@Valid @NotNull @RequestBody Book book) {
        return service.update(book);
    }
}
