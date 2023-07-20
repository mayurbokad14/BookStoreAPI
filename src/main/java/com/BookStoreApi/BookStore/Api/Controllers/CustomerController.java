package com.BookStoreApi.BookStore.Api.Controllers;

import com.BookStoreApi.BookStore.Models.Customer;
import com.BookStoreApi.BookStore.Models.Genre;
import com.BookStoreApi.BookStore.Repositories.CustomerRepository;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.crypto.spec.PSource;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@CrossOrigin
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @PostMapping("/bookstore/v1/customer")
    public ResponseEntity<String> addCustomer(@RequestBody Customer customer){
        System.out.println("Request come from/bookstore/v1/customer");

        JsonObject response = new JsonObject();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        //check if name ,email,address and phone_number is available in payload
        if(customer.getName() == null || customer.getEmail() == null ||customer.getAddress() == null || customer.getPhone_number() == null ){
            response.addProperty("status",HttpStatus.BAD_REQUEST.value());
            response.addProperty("success",false);
            response.addProperty("message", "Name, Email ,Address and Phone_Number are mandatory property");
            return new ResponseEntity<String>(response.toString(),headers , HttpStatus.BAD_REQUEST);
        }

        //check if name ,email,address and phone_number is sent empty
        if(customer.getName().isBlank() || customer.getEmail().isBlank() || customer.getAddress().isBlank() || customer.getPhone_number().isBlank()){
            response.addProperty("status",HttpStatus.BAD_REQUEST.value());
            response.addProperty("success" , false);
            response.addProperty("message","Name, Email ,Address and Phone_Number can not be empty");
            return  new ResponseEntity<String>(response.toString(),headers ,HttpStatus.BAD_REQUEST);
        }

        Pattern emailRegex = Pattern.compile("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$");
        Matcher emailMatcher = emailRegex.matcher(customer.getEmail());
        boolean isValidEmail = emailMatcher.find();

        if( ! isValidEmail){
            response.addProperty("status",HttpStatus.BAD_REQUEST.value());
            response.addProperty("success" , false);
            response.addProperty("message","Not a valid email address");
            return  new ResponseEntity<String>(response.toString(),headers ,HttpStatus.BAD_REQUEST);
        }

        Pattern phone_numberRegex = Pattern.compile("^([+])?([0-9]{1,3}\\-)?[0-9]{4,14}$");
        Matcher phone_numberMatcher = phone_numberRegex.matcher(customer.getPhone_number());
        boolean isValidPhone_Number = phone_numberMatcher.find();

        if(! isValidPhone_Number){
            response.addProperty("status", HttpStatus.BAD_REQUEST.value());
            response.addProperty("success", false);
            response.addProperty("message","Not a valid phone_number");
            return new ResponseEntity<String>(response.toString(), headers,HttpStatus.BAD_REQUEST);
        }
        if(customer.getName().length() > 100){
            response.addProperty("Status",HttpStatus.BAD_REQUEST.value());
            response.addProperty("success",false);
            response.addProperty("message","you are exceeding length of 100 characters for customer name");
            return new ResponseEntity<String>(response.toString(),headers,HttpStatus.BAD_REQUEST);
        }

        if(customer.getEmail().length() > 30){
            response.addProperty("Status",HttpStatus.BAD_REQUEST.value());
            response.addProperty("success",false);
            response.addProperty("message","you are exceeding length of 30 characters for customer email");
            return new ResponseEntity<String>(response.toString(),headers,HttpStatus.BAD_REQUEST);
        }

        if(customer.getAddress().length() > 200){
            response.addProperty("Status",HttpStatus.BAD_REQUEST.value());
            response.addProperty("success",false);
            response.addProperty("message","you are exceeding length of 200 characters for customer address");
            return new ResponseEntity<String>(response.toString(),headers,HttpStatus.BAD_REQUEST);
        }

        if(customer.getPhone_number().length() > 10){
            response.addProperty("Status",HttpStatus.BAD_REQUEST.value());
            response.addProperty("success",false);
            response.addProperty("message","you are exceeding length of 10 number for customer phone");
            return new ResponseEntity<String>(response.toString(),headers,HttpStatus.BAD_REQUEST);
        }

        customerRepository.save(
            new Customer(
                customer.getName(),
                customer.getEmail(),
                customer.getAddress(),
                customer.getPhone_number()
            )
        );

        //prepare response payload
        response.addProperty("status" , HttpStatus.OK.value());
        response.addProperty("success" , true);
        response.addProperty("message", "currently this endpoint is under construction ");
        return  new ResponseEntity<String>(response.toString(),headers, HttpStatus.OK);
    }

    @GetMapping("/bookstore/v1/customer")
    public ResponseEntity<String> getCustomer(@RequestParam(required = false) String name){

        List<Customer> customer = new ArrayList<Customer>();

        if (name== null){
            customerRepository.findAll().forEach(customer::add);
        }
        else {
            customerRepository.findByNameContainingIgnoreCase(name).forEach(customer::add);
        }


        JsonObject response = new JsonObject();
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(customer, new TypeToken<List<Customer>>() {}.getType());
        JsonArray jsonArray = element.getAsJsonArray();

        response.addProperty("status",HttpStatus.OK.value());
        response.addProperty("success", true);
        response.addProperty("message", "Data fetched successfully");
        response.add("data", jsonArray);

        return new ResponseEntity<String>(response.toString(), httpHeaders, HttpStatus.OK );
    }

}
