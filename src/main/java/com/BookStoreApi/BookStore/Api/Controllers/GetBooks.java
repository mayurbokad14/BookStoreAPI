package com.BookStoreApi.BookStore.Api.Controllers;

import org.springframework.http.HttpHeaders;
import java.util.ArrayList;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<String> getBooks(@RequestParam(required = false) String name, @RequestParam(required = false) Integer maxItems ){

        List<Book> books=new ArrayList<Book>();

        
        if(name == null && maxItems == null){
            //name and maxItems parameter is missing
            booksRepository.findAll().forEach(books::add);
        }
        else if(name != null && maxItems == null){
            //name is present, but maxItems parameter is missing
            booksRepository.findByNameContainingIgnoreCase(name).forEach(books::add);
        }
        else if( name == null && maxItems !=null){
            //name is missing but maxItems parameter is present
            //select * from books limit `maxItems`
            Pageable limit = PageRequest.of(0,maxItems);
            booksRepository.findAll(limit).forEach(books::add);
        }
        else{
            // both name and maxItems paramter is present
            //select * from books where name like '%name%' limit `maxItems`
            booksRepository.findByNameContainingIgnoreCase(name).forEach(books::add);
        }
        

        Gson gson = new Gson();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<String>(gson.toJson(books), headers, HttpStatus.OK);
    }
    
}
