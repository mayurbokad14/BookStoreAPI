package com.BookStoreApi.BookStore.Models;

import jakarta.persistence.*;

@Entity
@Table(name ="order_items")
public class OrderItem {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long order_item_id;

    @ManyToOne
    @JoinColumn(name = "isbn")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(nullable = false)
    private long quantity;

    @Column(nullable = false)
    private double price;

    public long getOrder_item_id() {
        return order_item_id;
    }

    public void setOrder_item_id(long order_item_id) {
        this.order_item_id = order_item_id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public OrderItem(long order_item_id, Book book, Order order, long quantity, double price) {
        this.order_item_id = order_item_id;
        this.book = book;
        this.order = order;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderItem() {
    }
}
