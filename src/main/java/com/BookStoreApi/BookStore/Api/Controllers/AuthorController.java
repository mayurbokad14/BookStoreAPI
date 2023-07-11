package com.BookStoreApi.BookStore.Api.Controllers;

import com.BookStoreApi.BookStore.Models.Author;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AuthorController{
    @PostMapping("/bookstore/v1/author")
    public ResponseEntity<String> addAuthor(@RequestBody Author author){

        return new ResponseEntity<String>("Hello world" , HttpStatus.OK);


    }


}