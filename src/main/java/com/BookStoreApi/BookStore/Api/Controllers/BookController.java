package com.BookStoreApi.BookStore.Api.Controllers;
import com.BookStoreApi.BookStore.Beans.RequestPayloadBook;
import com.BookStoreApi.BookStore.Models.Author;
import com.BookStoreApi.BookStore.Models.Book;
import com.BookStoreApi.BookStore.Models.Genre;
import com.BookStoreApi.BookStore.Repositories.AuthorRepository;
import com.BookStoreApi.BookStore.Repositories.BooksRepository;
import com.BookStoreApi.BookStore.Repositories.GenreRepository;
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

import java.util.Date;


@RestController
@CrossOrigin
public class BookController {

    @Autowired
    BooksRepository booksRepository;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    AuthorRepository authorRepository;

   /* @PostMapping("/bookstore/v1/book")
    public ResponseEntity<String> addNewBook(@RequestBody RequestPayloadBook requestPayloadBook) {

        JsonObject response = new JsonObject();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        Author author;
        Genre genre;
        Date publication_date;

        try {
            author = authorRepository.findById(requestPayloadBook.getAuthor_id()).get();
        } catch (Exception e) {
            response.addProperty("status", HttpStatus.BAD_REQUEST.value());
            response.addProperty("success", false);
            response.addProperty("message", "Author not found in inventory");
            return new ResponseEntity<String>(response.toString(), httpHeaders, HttpStatus.BAD_REQUEST);
        }

        try {
            genre = genreRepository.findById(requestPayloadBook.getGenre_id()).get();
        } catch (Exception e) {
            response.addProperty("status", HttpStatus.BAD_REQUEST.value());
            response.addProperty("success", false);
            response.addProperty("message", "Genre not found in inventory");
            return new ResponseEntity<String>(response.toString(), httpHeaders, HttpStatus.BAD_REQUEST);
        }

        try {
            publication_date = new Date(requestPayloadBook.getPublication_date());
        } catch (Exception e) {
            response.addProperty("status", HttpStatus.BAD_REQUEST.value());
            response.addProperty("success", false);
            response.addProperty("message", "Entered future date");
            return new ResponseEntity<String>(response.toString(), httpHeaders, HttpStatus.BAD_REQUEST);
        }

        //Book book = null;
        if (requestPayloadBook.getIsbn() == null) {
            response.addProperty("status", HttpStatus.BAD_REQUEST.value());
            response.addProperty("success", false);
            response.addProperty("message ", "isbn are mandatory");
            return new ResponseEntity<String>(response.toString(), httpHeaders, HttpStatus.BAD_REQUEST);
        }

        if (requestPayloadBook.getIsbn().isBlank()){
            response.addProperty("status" ,HttpStatus.BAD_REQUEST.value());
            response.addProperty("success", false);
            response.addProperty("message" , "Isbn can not be empty");
            return new ResponseEntity<String>(response.toString(), httpHeaders, HttpStatus.BAD_REQUEST);
        }

        if (requestPayloadBook.getIsbn().length() > 20){
            response.addProperty("status" , HttpStatus.BAD_REQUEST.value());
            response.addProperty("success", false);
            response.addProperty("message" ,"you are exceeding length of 20 character for Isbn" );
            return new ResponseEntity<String>(response.toString(), httpHeaders, HttpStatus.BAD_REQUEST);
        }

        if(requestPayloadBook.getTitle() == null){
            response.addProperty("status" , HttpStatus.BAD_REQUEST.value());
            response.addProperty("success", false);
            response.addProperty("message" ,"Title are mandatory" );
            return new ResponseEntity<String>(response.toString(), httpHeaders, HttpStatus.BAD_REQUEST);
        }

        if (requestPayloadBook.getTitle().isBlank()){
            response.addProperty("status" ,HttpStatus.BAD_REQUEST.value());
            response.addProperty("success", false);
            response.addProperty("message" , "Title can not be empty");
            return new ResponseEntity<String>(response.toString(), httpHeaders, HttpStatus.BAD_REQUEST);
        }

        if (requestPayloadBook.getTitle().length() > 255){
            response.addProperty("status" , HttpStatus.BAD_REQUEST.value());
            response.addProperty("success", false);
            response.addProperty("message" ,"you are exceeding length of 20 character for Isbn" );
            return new ResponseEntity<String>(response.toString(), httpHeaders, HttpStatus.BAD_REQUEST);
        }

        if(requestPayloadBook.getPrice() <= 0.0){
            response.addProperty("status" ,HttpStatus.BAD_REQUEST.value());
            response.addProperty("success" ,false);
            response.addProperty("message" ,"Invalid value for the price");
            return new ResponseEntity<String>(response.toString(), httpHeaders, HttpStatus.BAD_REQUEST);
        }

        if(requestPayloadBook.getDescription() == null){
            response.addProperty("status" ,HttpStatus.BAD_REQUEST.value());
            response.addProperty("success" ,false);
            response.addProperty("message" ,"Description field is required");
            return new ResponseEntity<String>(response.toString(), httpHeaders, HttpStatus.BAD_REQUEST);
        }
        if(requestPayloadBook.getDescription().isBlank()){
            response.addProperty("status" ,HttpStatus.BAD_REQUEST.value());
            response.addProperty("success" ,false);
            response.addProperty("message" ,"Description cannot be empty");
            return new ResponseEntity<String>(response.toString(), httpHeaders, HttpStatus.BAD_REQUEST);
        }

        if(requestPayloadBook.getDescription().length() > 255 ){
            response.addProperty("status" ,HttpStatus.BAD_REQUEST.value());
            response.addProperty("success" ,false);
            response.addProperty("message" ,"Exceeding length of description field about 255 character");
            return new ResponseEntity<String>(response.toString(), httpHeaders, HttpStatus.BAD_REQUEST);
        }

        System.out.println(requestPayloadBook.getPrice());

        Book book = new Book(
            requestPayloadBook.getIsbn().trim(),
            requestPayloadBook.getTitle().trim(),
            author,
            genre,
            publication_date,
            requestPayloadBook.getPrice(),
            requestPayloadBook.getDescription().trim()
        );

        //booksRepository.save(book);

        System.out.println(author.getName());

        response.addProperty("status",HttpStatus.OK.value());
        response.addProperty("success",true);
        response.addProperty("message","New book added to the inventory : " + requestPayloadBook.getTitle());

        return new ResponseEntity<String>(response.toString(),httpHeaders, HttpStatus.OK);
    }*/

    @PostMapping("/bookstore/v1/book")
    public ResponseEntity<String> addNewBook(@RequestBody Book book){

        JsonObject response = new JsonObject();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        if(book == null){
            response.addProperty("status",HttpStatus.BAD_REQUEST.value());
            response.addProperty("success",false);
            response.addProperty("message", "Bad request");

            return new ResponseEntity<String>(response.toString(),headers, HttpStatus.BAD_REQUEST);
        }

        if(book.getAuthor() == null || book.getGenre() == null){
            response.addProperty("status",HttpStatus.BAD_REQUEST.value());
            response.addProperty("success",false);
            response.addProperty("message", "Bad payload, author and genre fields are required");
            return new ResponseEntity<String>(response.toString(),headers, HttpStatus.BAD_REQUEST);
        }


        Author author;
        Genre genre;

        try {
            author = authorRepository.findById(book.getAuthor().getAuthor_id()).get();
        }
        catch (Exception e){
            response.addProperty("status", HttpStatus.BAD_REQUEST.value());
            response.addProperty("success", false);
            response.addProperty("message", "Author not found in inventory");
            return new ResponseEntity<String>(response.toString(), headers, HttpStatus.BAD_REQUEST);
        }

        try {
            genre = genreRepository.findById(book.getGenre().getGenre_id()).get();
        }
        catch (Exception e){
            response.addProperty("status", HttpStatus.BAD_REQUEST.value());
            response.addProperty("success", false);
            response.addProperty("message", "Genre not found in inventory");
            return new ResponseEntity<String>(response.toString(), headers, HttpStatus.BAD_REQUEST);
        }

        if(book.getPublication_date() == null){
            response.addProperty("status", HttpStatus.BAD_REQUEST.value());
            response.addProperty("success", false);
            response.addProperty("message", "Publication date is required");
            return new ResponseEntity<String>(response.toString(), headers, HttpStatus.BAD_REQUEST);
        }

        Date current_date = new Date();

        if(book.getPublication_date().getTime() >  current_date.getTime() ){
            response.addProperty("status", HttpStatus.BAD_REQUEST.value());
            response.addProperty("success", false);
            response.addProperty("message", "Invalid date");
            return new ResponseEntity<String>(response.toString(), headers, HttpStatus.BAD_REQUEST);
        }

        if (book.getIsbn() == null) {
            response.addProperty("status", HttpStatus.BAD_REQUEST.value());
            response.addProperty("success", false);
            response.addProperty("message ", "isbn are mandatory");
            return new ResponseEntity<String>(response.toString(), headers, HttpStatus.BAD_REQUEST);
        }

        if (book.getIsbn().isBlank()){
            response.addProperty("status" ,HttpStatus.BAD_REQUEST.value());
            response.addProperty("success", false);
            response.addProperty("message" , "Isbn can not be empty");
            return new ResponseEntity<String>(response.toString(), headers, HttpStatus.BAD_REQUEST);
        }

        if (book.getIsbn().length() > 20){
            response.addProperty("status" , HttpStatus.BAD_REQUEST.value());
            response.addProperty("success", false);
            response.addProperty("message" ,"you are exceeding length of 20 character for Isbn" );
            return new ResponseEntity<String>(response.toString(), headers, HttpStatus.BAD_REQUEST);
        }

        if(book.getTitle() == null){
            response.addProperty("status" , HttpStatus.BAD_REQUEST.value());
            response.addProperty("success", false);
            response.addProperty("message" ,"Title are mandatory" );
            return new ResponseEntity<String>(response.toString(), headers, HttpStatus.BAD_REQUEST);
        }

        if (book.getTitle().isBlank()){
            response.addProperty("status" ,HttpStatus.BAD_REQUEST.value());
            response.addProperty("success", false);
            response.addProperty("message" , "Title can not be empty");
            return new ResponseEntity<String>(response.toString(), headers, HttpStatus.BAD_REQUEST);
        }

        if (book.getTitle().length() > 255){
            response.addProperty("status" , HttpStatus.BAD_REQUEST.value());
            response.addProperty("success", false);
            response.addProperty("message" ,"you are exceeding length of 20 character for Isbn" );
            return new ResponseEntity<String>(response.toString(), headers, HttpStatus.BAD_REQUEST);
        }

        if(book.getPrice() <= 0.0){
            response.addProperty("status" ,HttpStatus.BAD_REQUEST.value());
            response.addProperty("success" ,false);
            response.addProperty("message" ,"Invalid value for the price");
            return new ResponseEntity<String>(response.toString(), headers, HttpStatus.BAD_REQUEST);
        }

        if(book.getDescription() == null){
            response.addProperty("status" ,HttpStatus.BAD_REQUEST.value());
            response.addProperty("success" ,false);
            response.addProperty("message" ,"Description field is required");
            return new ResponseEntity<String>(response.toString(), headers, HttpStatus.BAD_REQUEST);
        }
        if(book.getDescription().isBlank()){
            response.addProperty("status" ,HttpStatus.BAD_REQUEST.value());
            response.addProperty("success" ,false);
            response.addProperty("message" ,"Description cannot be empty");
            return new ResponseEntity<String>(response.toString(), headers, HttpStatus.BAD_REQUEST);
        }

        if(book.getDescription().length() > 255 ){
            response.addProperty("status" ,HttpStatus.BAD_REQUEST.value());
            response.addProperty("success" ,false);
            response.addProperty("message" ,"Exceeding length of description field about 255 character");
            return new ResponseEntity<String>(response.toString(), headers, HttpStatus.BAD_REQUEST);
        }

        booksRepository.save(new Book(
            book.getIsbn().trim(),
            book.getTitle().trim(),
            book.getAuthor(),
            book.getGenre(),
            book.getPublication_date(),
            book.getPrice(),
            book.getDescription()
        ));

        //System.out.println(book.getPublication_date().toString());

        response.addProperty("status",HttpStatus.OK.value());
        response.addProperty("success",true);
        response.addProperty("message","New book added to the inventory : " + book.getTitle());

        return new ResponseEntity<String>(response.toString(),headers, HttpStatus.OK);

    }
}
