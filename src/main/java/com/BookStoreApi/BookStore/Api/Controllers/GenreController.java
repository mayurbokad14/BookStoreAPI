package com.BookStoreApi.BookStore.Api.Controllers;

import com.BookStoreApi.BookStore.Models.Genre;
import com.BookStoreApi.BookStore.Repositories.GenreRepository;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class GenreController {

    @Autowired
    GenreRepository genreRepository;

    @PostMapping("/bookstore/v1/genre")
    public ResponseEntity<String> addGenre(@RequestBody Genre genre){

        System.out.println("/Request come from/bookstore/v1/genre");

        JsonObject response = new JsonObject();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        //check if name is available in payload
        if(genre.getName() == null){
            //prepare payload for bad request
            response.addProperty("status" , HttpStatus.BAD_REQUEST.value());
            response.addProperty("success", false );
            response.addProperty("message", "Name is mandatory property");
            return new ResponseEntity<String>(response.toString(),headers,HttpStatus.BAD_REQUEST);
        }

        //check if name is sent empty
        if(genre.getName().isBlank()){
            //prepare payload for bad request
            response.addProperty("status",HttpStatus.BAD_REQUEST.value());
            response.addProperty("success",false);
            response.addProperty("message","Name can not be empty");
            return new ResponseEntity<String>(response.toString(),headers,HttpStatus.BAD_REQUEST);
        }
        if()

        genreRepository.save(
                new Genre(
                        genre.getName()
                )
        );

        //prepare response payload
        response.addProperty("status",HttpStatus.OK.value());
        response.addProperty("success" , true);
        response.addProperty("message" , "New Genre added to the library : " + genre.getName());

        return new ResponseEntity<String>(response.toString(),headers ,HttpStatus.OK);
    }

    @GetMapping("/bookstore/v1/genre")
    public ResponseEntity<String> getGenre(
            @RequestParam(required = false) Long genre_id,
            @RequestParam(required = false) Long maxRows,
            @RequestParam(required = false) Long pageNum
            ){

        List<Genre> genres = new ArrayList<Genre>();

        genreRepository.findAll().forEach(genres::add);

        JsonObject response = new JsonObject();
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(genres, new TypeToken<List<Genre>>() {}.getType());
        JsonArray jsonArray = element.getAsJsonArray();

        response.addProperty("status",HttpStatus.OK.value());
        response.addProperty("success", true);
        response.addProperty("message", "Data fetched successfully");
        response.add("data", jsonArray);


        return new ResponseEntity<String>(response.toString(), httpHeaders, HttpStatus.OK );

    }

}