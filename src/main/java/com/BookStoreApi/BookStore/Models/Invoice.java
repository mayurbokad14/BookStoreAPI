package com.BookStoreApi.BookStore.Models;

import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.util.Date;

@Entity
@Table(name = "Invoice")
public class Invoice {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long invoice_id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    public Order order;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date issue_date;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date due_date;

    @Column(nullable = false)
    private double total_amount;

    public long getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(long invoice_id) {
        this.invoice_id = invoice_id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Date getIssue_date() {
        return issue_date;
    }

    public void setIssue_date(Date issue_date) {
        this.issue_date = issue_date;
    }

    public Date getDue_date() {
        return due_date;
    }

    public void setDue_date(Date due_date) {
        this.due_date = due_date;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }

    public Invoice(Order order, Date issue_date, Date due_date, double total_amount) {
        this.order = order;
        this.issue_date = issue_date;
        this.due_date = due_date;
        this.total_amount = total_amount;
    }

    public Invoice() {
    }
}

