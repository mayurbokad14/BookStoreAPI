package com.BookStoreApi.BookStore.Beans;

public class RequestPayloadBook {

    private String isbn;
    private String title;
    private long author_id;
    private long genre_id;
    private long publication_date;
    private double price;
    private String description;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(long author_id) {
        this.author_id = author_id;
    }

    public long getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(long genre_id) {
        this.genre_id = genre_id;
    }

    public long getPublication_date() {
        return publication_date;
    }

    public void setPublication_date(long publication_date) {
        this.publication_date = publication_date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RequestPayloadBook(String isbn, String title, long author_id, long genre_id, long publication_date, double price, String description) {
        this.isbn = isbn;
        this.title = title;
        this.author_id = author_id;
        this.genre_id = genre_id;
        this.publication_date = publication_date;
        this.price = price;
        this.description = description;
    }

    public RequestPayloadBook() {
    }
}
