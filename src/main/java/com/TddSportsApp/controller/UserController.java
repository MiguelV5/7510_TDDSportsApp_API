package com.TddSportsApp.controller;

import com.TddSportsApp.controller.userDto.CreateUserDto;
import com.TddSportsApp.models.UserEntity;
import com.TddSportsApp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/users")
@RestController
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
    public Optional<UserEntity> getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @GetMapping("/{email}")
    public Optional<UserEntity> getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @GetMapping("/{username}")
    public Optional<UserEntity> getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable String id) {
        userService.deleteUser(id);

        return "Deleted user with id".concat(id);
    }
}
