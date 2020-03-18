package com.example.bookmanagement.Controllers;

import com.example.bookmanagement.Models.Book;
import com.example.bookmanagement.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/book")
public class BookControllers {

    private final BookService service;

    @Autowired
    public BookControllers(BookService service) {
        this.service = service;
    }

    @GetMapping
    public ArrayList<Book> query() {
        return (ArrayList<Book>) service.query();
    }

//    @GetMapping(path = "keyword/{keyword}")
//    public Iterable<Book> query(@PathVariable("keyword") String keyword) {
//        return service.query(keyword);
//    }

    @GetMapping(path = "{uuid}")
    public Optional<Book> query(@PathVariable("uuid") UUID uuid) {
        return service.query(uuid);
    }

    @GetMapping(path = "{title}/{author}/{publisher}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book add(@PathVariable("title") String title,
                    @PathVariable("author") String author,
                    @PathVariable("publisher") String publisher) {
        return service.add(title, author, publisher);
    }

    @GetMapping(path = "{title}/{author}/{publisher}/{pagesCount}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book add(@PathVariable("title") String title,
                    @PathVariable("author") String author,
                    @PathVariable("publisher") String publisher,
                    @PathVariable("pagesCount") Integer pagesCount) {
        return service.add(title, author, publisher, pagesCount);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book add(@NotNull @RequestBody Book book) {
        return service.add(book);
    }

    @DeleteMapping(path = "{uuid}")
    public void delete(@PathVariable("uuid") UUID uuid) {
        service.delete(uuid);
    }

//    @DeleteMapping(path = "title/{title}")
//    public void delete(@PathVariable("title") String title) {
//        service.delete(title, null, null);
//    }

//    @DeleteMapping(path = "author/{author}")
//    public List<Book> deleteByAuthor(String author) {
//        return service.delete(null, author, null);
//    }

//    @DeleteMapping(path = "publisher/{publisher}")
//    public List<Book> deleteByPublisher(String publisher) {
//        return service.delete(null, null, publisher);
//    }

//    @PutMapping
//    public boolean update(@Valid @NotNull @RequestBody Book book) {
//        return service.update(book);
//    }
}
