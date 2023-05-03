package com.BookStoreApi.BookStore.Api;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
    @GetMapping("/")
    public String helloworld(){
        return "Hello World\n";
    }
}
