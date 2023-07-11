package com.BookStoreApi.BookStore.Models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name ="orders")
public class Order {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long order_id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date order_date;

    @Column(nullable = false)
    private double total_amount;

    public long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }

    public Order(Customer customer, Date order_date, double total_amount) {
        this.customer = customer;
        this.order_date = order_date;
        this.total_amount = total_amount;
    }

    public Order() {
    }
}
