package com.tcs.training.orderms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "publishOn")
    private String publishOn;

    @Column(name = "publication")
    private String publication;

    @Column(name = "numberOfCopy")
    private Long numberOfCopy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishOn() {
        return publishOn;
    }

    public void setPublishOn(String publishOn) {
        this.publishOn = publishOn;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public Long getNumberOfCopy() {
        return numberOfCopy;
    }

    public void setNumberOfCopy(Long numberOfCopy) {
        this.numberOfCopy = numberOfCopy;
    }

    public void updateBook(Book updatedData) {
        if (updatedData.title != null || updatedData.title != "")
            this.title = updatedData.title;
        if (updatedData.author != null || updatedData.author != "")
            this.author = updatedData.author;
        if (updatedData.publishOn != null || updatedData.publishOn != "")
            this.publishOn = updatedData.publishOn;
        if (updatedData.publication != null || updatedData.publication != "")
            this.publication = updatedData.publication;
        if (updatedData.numberOfCopy != null || updatedData.numberOfCopy < 0)
            this.numberOfCopy = updatedData.numberOfCopy;
    }
}
