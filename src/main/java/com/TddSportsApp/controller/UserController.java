package com.TddSportsApp.controller;

import com.TddSportsApp.exceptions.UserNotFoundException;
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
    public ResponseEntity<UserEntity> createUser(@Valid @RequestBody CreateUserDto createUserDto) {
        return ResponseEntity.ok(userService.createUser(createUserDto));
    }

    @GetMapping("")
    public List<UserEntity> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(userService.getUserById(Long.parseLong(id)));
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/me")
    public UserEntitySuperDto getLoggedUserInfo() {
        System.out.println("getLoggedUserInfo");
        return userService.getLoggedUserInfo();
    }

    @PutMapping("")
    public ResponseEntity<UserEntity> updateUser(@Valid @RequestBody CreateUserDto userEntity) {
        return ResponseEntity.ok(userService.updateUser(userEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable String id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
