package com.BookStoreApi.BookStore.Api.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.BookStoreApi.BookStore.Api.Models.Book;
import com.BookStoreApi.BookStore.Api.Models.BooksRepository;



@RestController
public class CreateBook {

    @Autowired
    private BooksRepository booksRepository;

    @PostMapping("/v1/api/book")
    public ResponseEntity<String> addNewBook(@RequestBody Book book){

        Book b = new Book(book.getIsbn(),book.getName(),book.getDescription(),book.getPrice(),book.getQuantity());

        booksRepository.save(b);

        return new ResponseEntity<String>("New Book Added", HttpStatus.CREATED);
        
    }
}