package com.BookStoreApi.BookStore.Api.Controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
    @GetMapping("/")
    public String helloworld() {
        return "Hello World\n";
    }

    @GetMapping("/status")
    public String status() {
        return "Under Construction !\n";
    }

}
