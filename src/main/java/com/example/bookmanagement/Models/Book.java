package com.example.bookmanagement.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    @Column(nullable = false)
    private String title;

    @Column(nullable = true)
    private String publisher;

    @Column(nullable = true)
    private Integer pagesCount;

    @Column(nullable = false)
    private String author;

    public Book(@JsonProperty("uuid") UUID uuid,
                @JsonProperty("title") String title,
                @JsonProperty("publisher") String publisher,
                @JsonProperty("pagesCount") Integer pagesCount,
                @JsonProperty("author") String author) {
        this.uuid = Objects.requireNonNullElse(uuid, UUID.randomUUID());
        this.title = Objects.requireNonNullElse(title, "");
        this.publisher = Objects.requireNonNullElse(publisher, "");
        this.pagesCount = Objects.requireNonNullElse(pagesCount, 0);
        this.author = Objects.requireNonNullElse(author, "");
    }

    public Book() {
        super();
    }

    public Book(String title, String author, String publisher) {
        this.title = Objects.requireNonNullElse(title, "");
        this.publisher = Objects.requireNonNullElse(publisher, "");
        this.author = Objects.requireNonNullElse(author, "");
        pagesCount = null;
        uuid = UUID.randomUUID();
    }

    public Book(String title, String author, String publisher, Integer pagesCount) {
        this.uuid = UUID.randomUUID();
        this.title = Objects.requireNonNullElse(title, "");
        this.publisher = Objects.requireNonNullElse(publisher, "");
        this.pagesCount = Objects.requireNonNullElse(pagesCount, 0);
        this.author = Objects.requireNonNullElse(author, "");
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPagesCount() {
        return pagesCount;
    }

    public void setPagesCount(int pagesCount) {
        this.pagesCount = pagesCount;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean contains(String keyword) {
        return toString().contains(keyword);
    }

    public boolean equals(Book anotherBook) {
        UUID anotherUUID = anotherBook.getUuid();
        String anotherAuthor = anotherBook.getAuthor();
        String anotherPublisher = anotherBook.getPublisher();
        String anotherTitle = anotherBook.getTitle();
        int anotherPages = anotherBook.getPagesCount();
        return uuid.compareTo(anotherUUID) == 0 &&
                author.contentEquals(anotherAuthor) &&
                publisher.contentEquals(anotherPublisher) &&
                title.contentEquals(anotherTitle) &&
                anotherPages == anotherBook.getPagesCount();
    }

    @Override
    public String toString() {
        return uuid.toString() + "\t" + title + "\t" + author + "\t" + publisher + "\t" + pagesCount;
    }
}
