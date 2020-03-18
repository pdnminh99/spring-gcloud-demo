//package com.example.bookmanagement.DataAccessObjects;
//
//import com.example.bookmanagement.Models.Book;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//import java.util.UUID;
//
//@Repository("raw")
//public class RawBookRepository implements BookOperations {
//
//    private final ArrayList<Book> books;
//
//    public RawBookRepository() {
//        books = new ArrayList<>();
//        books.add(new Book(null, "Harry Potter", "Hogward Express", 10, "J.K.Rowling"));
//    }
//
//    @Override
//    public List<Book> query(String keyword) {
//        ArrayList<Book> booksMatchKeyword = new ArrayList<>();
//        for (Book book : books) {
//            if (book.contains(keyword)) {
//                booksMatchKeyword.add(book);
//            }
//        }
//        return booksMatchKeyword;
//    }
//
//    @Override
//    public List<Book> query() {
//        return books;
//    }
//
//    @Override
//    public Book query(UUID uuid) {
//        for (Book book : books) {
//            if (book.getUuid().compareTo(uuid) == 0) {
//                return book;
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public boolean add(Book book) {
//        if (book == null) {
//            return false;
//        }
//        books.add(book);
//        return true;
//    }
//
//    @Override
//    public Book delete(UUID uuid) {
//        int size = books.size();
//        Book currentBook;
//        for (int i = 0; i < size; i++) {
//            currentBook = books.get(i);
//            if (currentBook.getUuid().compareTo(uuid) == 0) {
//                books.remove(i);
//                return currentBook;
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public List<Book> delete(String title, String author, String publisher) {
//        int size = books.size();
//        ArrayList<Book> deletedBooks = new ArrayList<>();
//        Book currentBook;
//        String currentTitle;
//        String currentAuthor;
//        String currentPublisher;
//
//        for (int i = 0; i < size; ) {
//            currentBook = books.get(i);
//            currentTitle = currentBook.getTitle();
//            currentAuthor = currentBook.getAuthor();
//            currentPublisher = currentBook.getPublisher();
//            if (title == currentTitle || author == currentAuthor || publisher == currentPublisher) {
//                deletedBooks.add(currentBook);
//                books.remove(i);
//                continue;
//            }
//            i++;
//        }
//        return deletedBooks;
//    }
//
//    @Override
//    public boolean update(Book newBook) {
//        if (newBook == null) {
//            return false;
//        }
//        UUID newBookUUID = newBook.getUuid();
//        UUID currentUUID;
//        int size = books.size();
//
//        for (int i = 0; i < size; i++) {
//            currentUUID = books.get(i).getUuid();
//            if (currentUUID.compareTo(newBookUUID) == 0) {
//                books.set(i, newBook);
//                return true;
//            }
//        }
//        return false;
//    }
//
//}
