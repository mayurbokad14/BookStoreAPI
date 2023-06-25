package com.BookStoreApi.BookStore.Api.Controllers;

import org.springframework.http.HttpHeaders;
import java.util.ArrayList;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BookStoreApi.BookStore.Api.Models.Book;
import com.BookStoreApi.BookStore.Api.Models.BooksRepository;
import com.google.gson.Gson;

@CrossOrigin
@RestController
public class GetBooks {

    @Autowired
    private BooksRepository booksRepository;

    @GetMapping("/v1/api/book")
    public ResponseEntity<String> getBooks(){

        List<Book> books=new ArrayList<Book>();
        booksRepository.findAll().forEach(books::add);

        Gson gson = new Gson();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<String>(gson.toJson(books), headers, HttpStatus.OK);
    }
    
}
