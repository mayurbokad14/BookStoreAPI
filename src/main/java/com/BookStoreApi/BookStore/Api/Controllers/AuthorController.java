package com.BookStoreApi.BookStore.Api.Controllers;

import com.BookStoreApi.BookStore.Models.Author;
import com.google.gson.JsonObject;
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

        response.addProperty("status",HttpStatus.OK.value());
        response.addProperty("success", true);
        response.addProperty("message", "Currently this endpoint is under construction");

        return new ResponseEntity<String>(response.toString(), headers, HttpStatus.OK);

    }

}