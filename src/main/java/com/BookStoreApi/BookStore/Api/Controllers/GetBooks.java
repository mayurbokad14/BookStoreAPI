package com.BookStoreApi.BookStore.Api.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
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
import com.google.gson.JsonObject;

@CrossOrigin
@RestController
public class GetBooks {

    @Autowired
    private BooksRepository booksRepository;

    @GetMapping("/v1/api/book")
    public ResponseEntity<String> getBooks(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) Integer maxItems,
        @RequestParam(required = false) Integer pageNum ){

        int maxItemsPerPage = maxItems == null ? 10 : maxItems;
        int pageNumber = pageNum == null ? 0 : (pageNum > 0 ? pageNum -1 : 0);
        Pageable limit = PageRequest.of(pageNumber, maxItemsPerPage);

        Page<Book> page;
        Gson gson = new Gson();

        if(name == null){
            page = booksRepository.findAll(limit);
        }
        else{
            page = booksRepository.findByNameContainingIgnoreCase(name, limit);
        }

        //int totalPages = page.getTotalPages();
        //JsonObject responseData = new JsonObject();

        //responseData.addProperty("totalPages", totalPages);
        //responseData.addProperty("content", gson.toJson(page));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<String>(gson.toJson(page), headers, HttpStatus.OK);
    }
    
}
