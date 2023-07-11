package com.BookStoreApi.BookStore.Api.Controllers;

import com.BookStoreApi.BookStore.Models.Genre;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class GenreController {
    @PostMapping("/bookstore/v1/genre")
    public ResponseEntity<String> addGenre(@RequestBody Genre genre){
        return new ResponseEntity<String>("Hello world" , HttpStatus.OK);
    }
}