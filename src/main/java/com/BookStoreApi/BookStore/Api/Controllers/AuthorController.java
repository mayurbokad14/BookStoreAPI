package com.BookStoreApi.BookStore.Api.Controllers;

import com.BookStoreApi.BookStore.Models.Author;
import com.BookStoreApi.BookStore.Repositories.AuthorRepository;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AuthorController{

    @Autowired
    AuthorRepository authorRepository;

    @PostMapping("/bookstore/v1/author")
    public ResponseEntity<String> addAuthor(@RequestBody Author author){

        System.out.println("Request came for /bookstore/v1/author");

        JsonObject response = new JsonObject();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        if(author.getName() == null || author.getBio() == null){
            response.addProperty("status", HttpStatus.BAD_REQUEST.value());
            response.addProperty("success", false);
            response.addProperty("message", "Name and Bio are mandatory properties");
            return new ResponseEntity<String>(response.toString(),headers,HttpStatus.BAD_REQUEST);
        }

        if(author.getName().isBlank() || author.getBio().isBlank()){
            response.addProperty("status", HttpStatus.BAD_REQUEST.value());
            response.addProperty("success", false);
            response.addProperty("message", "Name or Bio can not be empty");
            return new ResponseEntity<String>(response.toString(),headers,HttpStatus.BAD_REQUEST);
        }

        if(author.getName().length() > 100){
            response.addProperty("Status",HttpStatus.BAD_REQUEST.value());
            response.addProperty("success",false);
            response.addProperty("message","you are exceeding length of 20 characters for author name");
            return new ResponseEntity<String>(response.toString(),headers,HttpStatus.BAD_REQUEST);
        }

        if(author.getBio().length() > 255){
            response.addProperty("status", HttpStatus.BAD_REQUEST.value());
            response.addProperty("success", false);
            response.addProperty("message", "You are exceeding length of 255 characters for author bio-data");
            return new ResponseEntity<String>(response.toString(),headers,HttpStatus.BAD_REQUEST);
        }

        //create a new Author object
        authorRepository.save(
                new Author(
                        author.getName(),
                        author.getBio()
                )
        );


        response.addProperty("status",HttpStatus.OK.value());
        response.addProperty("success", true);
        response.addProperty("message", "New Author added to library : " + author.getName());

        return new ResponseEntity<String>(response.toString(), headers, HttpStatus.OK);

    }

}