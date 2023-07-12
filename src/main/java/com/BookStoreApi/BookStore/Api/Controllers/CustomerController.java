package com.BookStoreApi.BookStore.Api.Controllers;

import com.BookStoreApi.BookStore.Models.Customer;
import com.google.gson.JsonObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.spec.PSource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@CrossOrigin
public class CustomerController {
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

        //prepare response payload
        response.addProperty("status" , HttpStatus.OK.value());
        response.addProperty("success" , true);
        response.addProperty("message", "currently this endpoint is under construction ");
        return  new ResponseEntity<String>(response.toString(),headers, HttpStatus.OK);
    }
}
