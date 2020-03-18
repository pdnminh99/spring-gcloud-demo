package com.example.bookmanagement.DataAccessObjects;

import com.example.bookmanagement.Models.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface BookOperations extends CrudRepository<Book, UUID> {

//    Iterable<Book> query(String keyword);

//    List<Book> query();

//    Book query(UUID uuid);

//    boolean add(Book book);

//    default boolean add(String title, String author, String publisher) {
//        return add(new Book(null, title, publisher, null, author));
//    }

//    default boolean add(String title, String author, String publisher, Integer pagesCount) throws NullPointerException {
//        if (Objects.isNull(title) || Objects.isNull(author) || Objects.isNull(publisher)) {
//            throw new NullPointerException("Title, author or publisher should not be null.");
//        }
//        if (Objects.nonNull(pagesCount)) {
//            return add(title, author, publisher);
//        }
//        return add(new Book(null, title, publisher, pagesCount, author));
//    }

//    List<Book> delete(String title, String author, String publisher);

//    default boolean update(UUID uuid, String newTitle, String newAuthor, String newPublisher, Integer newPagesCount) {
//        return update(new Book(uuid, newTitle, newPublisher, newPagesCount, newAuthor));
//    }
//
//    boolean update(Book newBook);
}
