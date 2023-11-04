package com.TddSportsApp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class GeneralController {

    @GetMapping("/")
    public ResponseEntity<String> index() {
        return new ResponseEntity<>(
                "Welcome to TDD Sports App.\nRefer to /swagger-ui.html for API documentation.\nAccess to the API is restricted to authorized users only, please register and login to gain access.",
                org.springframework.http.HttpStatus.OK);
    }

}
