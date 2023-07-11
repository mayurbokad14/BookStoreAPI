package com.BookStoreApi.BookStore.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "authors")
public class Author {
    
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long author_id;
    
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String bio;

    public double getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(long author_id) {
        this.author_id = author_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Author(long author_id, String name, String bio) {
        this.author_id = author_id;
        this.name = name;
        this.bio = bio;
    }

    public Author() {
    }
}
