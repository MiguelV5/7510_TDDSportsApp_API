package com.TddSportsApp.service;

import com.TddSportsApp.controller.request.CreateUserDto;
import com.TddSportsApp.models.ERole;
import com.TddSportsApp.models.RoleEntity;
import com.TddSportsApp.models.UserEntity;
import com.TddSportsApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity createUser(CreateUserDto createUserDto){
        RoleEntity role = RoleEntity.builder()
                .name(ERole.valueOf(createUserDto.getRole()))
                .build();

        UserEntity userEntity = UserEntity.builder()
                .username(createUserDto.getUsername())
                .password(createUserDto.getPassword())
                .email(createUserDto.getEmail())
                .role(role)
                .build();

        userRepository.save(userEntity);
        return userEntity;
    }

    public void deleteUser(String id){
        userRepository.deleteById(Long.parseLong(id));
    }

    public List<UserEntity> getUsers() {
        return (List<UserEntity>) userRepository.findAll();
    }

    public Optional<UserEntity> getUserById(String id) {
        return userRepository.findById(Long.parseLong(id));
    }
}
