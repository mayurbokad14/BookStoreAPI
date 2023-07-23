package com.BookStoreApi.BookStore.Api.Controllers;

import com.BookStoreApi.BookStore.Models.Author;
import com.BookStoreApi.BookStore.Models.Genre;
import com.BookStoreApi.BookStore.Repositories.AuthorRepository;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.element.Name;
import java.util.ArrayList;
import java.util.List;
@RestController
@CrossOrigin
public class AuthorController {

    @Autowired
    AuthorRepository authorRepository;



    @PostMapping("/bookstore/v1/author")
    public ResponseEntity<String> addAuthor(@RequestBody Author author) {

        System.out.println("Request came for /bookstore/v1/author");

        JsonObject response = new JsonObject();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        if (author.getName() == null || author.getBio() == null) {
            response.addProperty("status", HttpStatus.BAD_REQUEST.value());
            response.addProperty("success", false);
            response.addProperty("message", "Name and Bio are mandatory properties");
            return new ResponseEntity<String>(response.toString(), headers, HttpStatus.BAD_REQUEST);
        }

        if (author.getName().isBlank() || author.getBio().isBlank()) {
            response.addProperty("status", HttpStatus.BAD_REQUEST.value());
            response.addProperty("success", false);
            response.addProperty("message", "Name or Bio can not be empty");
            return new ResponseEntity<String>(response.toString(), headers, HttpStatus.BAD_REQUEST);
        }

        if (author.getName().length() > 100) {
            response.addProperty("Status", HttpStatus.BAD_REQUEST.value());
            response.addProperty("success", false);
            response.addProperty("message", "you are exceeding length of 100 characters for author name");
            return new ResponseEntity<String>(response.toString(), headers, HttpStatus.BAD_REQUEST);
        }

        if (author.getBio().length() > 255 ) {
            response.addProperty("status", HttpStatus.BAD_REQUEST.value());
            response.addProperty("success", false);
            response.addProperty("message", "You are exceeding length of 255 characters for author bio-data");
            return new ResponseEntity<String>(response.toString(), headers, HttpStatus.BAD_REQUEST);
        }

        //create a new Author object
        authorRepository.save(
            new Author(
                author.getName(),
                author.getBio()
            )
        );

        response.addProperty("status", HttpStatus.OK.value());
        response.addProperty("success", true);
        response.addProperty("message", "New Author added to library : " + author.getName());

        return new ResponseEntity<String>(response.toString(), headers, HttpStatus.OK);
    }

    @GetMapping("/bookstore/v1/author")
    public ResponseEntity<String> getAuthor(@RequestParam(required = false) String name ) {

        List<Author> author = new ArrayList<Author>();

        if(name == null){
            //select * from authors
            authorRepository.findAll().forEach(author::add);
        }
        else {
            //select * from authors where name like '%name%';
            authorRepository.findByNameContainingIgnoreCase(name).forEach(author::add);
        }

        JsonObject response = new JsonObject();
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(author, new TypeToken<List<Author>>() {
        }.getType());
        JsonArray jsonArray = element.getAsJsonArray();


        response.addProperty("status", HttpStatus.OK.value());
        response.addProperty("success", true);
        response.addProperty("message", "Data fetched successfully");
        response.add("data", jsonArray);

        return new ResponseEntity<String>(response.toString(), httpHeaders, HttpStatus.OK);

    }
}