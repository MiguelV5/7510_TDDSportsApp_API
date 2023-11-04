package com.TddSportsApp.service;

import com.TddSportsApp.controller.dto.CreateUserDto;
import com.TddSportsApp.models.ERole;
import com.TddSportsApp.models.UserEntity;
import com.TddSportsApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public UserEntity createUser(CreateUserDto createUserDto) {
        UserEntity userEntity = UserEntity.builder()
                .username(createUserDto.getUsername())
                .password(passwordEncoder.encode(createUserDto.getPassword()))
                .email(createUserDto.getEmail())
                .role(ERole.valueOf(createUserDto.getRole()))
                .build();

        userRepository.save(userEntity);
        return userEntity;
    }

    public void deleteUser(String id) {
        userRepository.deleteById(Long.parseLong(id));
    }

    public List<UserEntity> getUsers() {
        return (List<UserEntity>) userRepository.findAll();
    }

    public Optional<UserEntity> getUserById(String id) {
        return userRepository.findById(Long.parseLong(id));
    }

    public Optional<UserEntity> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<UserEntity> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
