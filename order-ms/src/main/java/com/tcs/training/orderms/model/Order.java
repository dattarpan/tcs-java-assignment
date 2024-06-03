package com.tcs.training.orderms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "bookId")
    private Long bookId;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "orderOn")
    private String orderOn;

    @Column(name = "returnDate")
    private String returnDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOrderOn() {
        return orderOn;
    }

    public void setOrderOn(String orderOn) {
        this.orderOn = orderOn;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public void updateBook(Book updatedData) {
        if (updatedData.bookId != null || updatedData.bookId != "")
            this.bookId = updatedData.bookId;
        if (updatedData.userId != null || updatedData.userId != "")
            this.userId = updatedData.userId;
        if (updatedData.orderOn != null || updatedData.orderOn != "")
            this.orderOn = updatedData.orderOn;
        if (updatedData.returnDate != null || updatedData.returnDate != "")
            this.returnDate = updatedData.returnDate;
    }
}
