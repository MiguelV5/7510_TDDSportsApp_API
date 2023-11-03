package com.TddSportsApp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class GeneralController {

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<String> index() {
        return new ResponseEntity<>(
                "Welcome to TDD Sports App. Refer to /swagger-ui.html for API documentation.",
                org.springframework.http.HttpStatus.OK);
    }

}
