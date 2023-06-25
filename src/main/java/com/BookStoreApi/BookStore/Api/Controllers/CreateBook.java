package com.BookStoreApi.BookStore.Api.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.BookStoreApi.BookStore.Api.Models.Book;
import com.BookStoreApi.BookStore.Api.Models.BooksRepository;
import com.google.gson.JsonObject;


@RestController
public class CreateBook {

    @Autowired
    private BooksRepository booksRepository;

    @PostMapping("/v1/api/book")
    public ResponseEntity<String> addNewBook(@RequestBody Book book){

        Book b = new Book(book.getIsbn(),book.getName(),book.getDescription(),book.getPrice(),book.getQuantity());

        booksRepository.save(b);

        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("status", HttpStatus.CREATED.value());
        jsonObject.addProperty("success", true);
        jsonObject.addProperty("msg", "new book added to the inventory " + book.getName());

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        System.out.println(jsonObject.toString());

        return new ResponseEntity<String>(jsonObject.toString(), headers, HttpStatus.OK);
        
    }
}