package com.TddSportsApp.controller;

import com.TddSportsApp.models.dto.CreateUserDto;
import com.TddSportsApp.models.UserEntity;
import com.TddSportsApp.models.dto.UserEntitySuperDto;
import com.TddSportsApp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserDto createUserDto) {
        return ResponseEntity.ok(userService.createUser(createUserDto));
    }

    @GetMapping("")
    public List<UserEntity> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public UserEntity getUserById(@PathVariable String id) {
        return userService.getUserById(Long.parseLong(id));
    }

    @GetMapping("/me")
    public UserEntitySuperDto getLoggedUserInfo() {
        System.out.println("getLoggedUserInfo");
        return userService.getLoggedUserInfo();
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable String id) {
        userService.deleteUser(id);

        return "Deleted user with id".concat(id);
    }
}
