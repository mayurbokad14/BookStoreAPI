package com.BookStoreApi.BookStore.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {
    
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long customer_id;
    
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phone_number;

    public long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(long customer_id) {
        this.customer_id = customer_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public Customer(String name, String email, String address, String phone_number) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone_number = phone_number;
    }
    public Customer() {
    }
}
