package com.BookStoreApi.BookStore.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    @OneToOne
    @JoinColumn(name = "isbn")
    private Book book;

    @Column(nullable = false)
    private  double quantity;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Inventory(Book book, double quantity) {
        this.book = book;
        this.quantity = quantity;
    }

    public Inventory() {
    }
}
