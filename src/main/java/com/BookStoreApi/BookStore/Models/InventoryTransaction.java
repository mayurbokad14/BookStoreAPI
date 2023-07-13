package com.BookStoreApi.BookStore.Models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "inventory_transactions")
public class InventoryTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long transaction_id;

    @ManyToOne
    @JoinColumn(name = "isbn")
    private  Book book;


    @Column(nullable = false)
    private Date transaction_date;

    @Column(nullable = false)
    private String transaction_type;

    @Column(nullable = false)
    private  long quantity;

    public long getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(long transaction_id) {
        this.transaction_id = transaction_id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(Date transaction_date) {
        this.transaction_date = transaction_date;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public InventoryTransaction(Book book, Date transaction_date, String transaction_type, long quantity) {
        this.book = book;
        this.transaction_date = transaction_date;
        this.transaction_type = transaction_type;
        this.quantity = quantity;
    }

    public InventoryTransaction() {
    }
}
