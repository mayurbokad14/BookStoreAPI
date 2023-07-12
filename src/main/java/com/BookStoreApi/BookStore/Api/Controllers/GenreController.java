package com.BookStoreApi.BookStore.Api.Controllers;

import com.BookStoreApi.BookStore.Models.Genre;
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
public class GenreController {
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

        //prepare response payload
        response.addProperty("status",HttpStatus.OK.value());
        response.addProperty("success" , true);
        response.addProperty("message" , "Currently this endpoint is under construction");

        return new ResponseEntity<String>(response.toString(),headers ,HttpStatus.OK);
    }

}