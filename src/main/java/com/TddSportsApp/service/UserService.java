package com.TddSportsApp.service;

import com.TddSportsApp.models.dto.CreateUserDto;
import com.TddSportsApp.exceptions.UserNotFoundException;
import com.TddSportsApp.models.UserEntity;
import com.TddSportsApp.models.dto.ResultWithEventDetailsDto;
import com.TddSportsApp.models.dto.UserEntitySuperDto;
import com.TddSportsApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
        String encodedPassword = passwordEncoder.encode(createUserDto.getPassword());
        createUserDto.setPassword(encodedPassword);
        UserEntity userEntity = createUserDto.toUserEntity();

        userRepository.save(userEntity);
        return userEntity;
    }

    public UserEntity getLoggedUser() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(username).get();
    }

    public void deleteUser(String id) {
        this.getUserById(Long.parseLong(id));
        userRepository.deleteById(Long.parseLong(id));
    }

    public List<UserEntity> getUsers() {
        return (List<UserEntity>) userRepository.findAll();
    }

    public UserEntity getUserById(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        return user.get();
    }

    public List<ResultWithEventDetailsDto> getUserResultsWithEventDetails(UserEntity user) {
        return user.getResults()
                .stream()
                .map(result -> new ResultWithEventDetailsDto(
                        result.getId(),
                        result.getOfficial(),
                        result.getTime(),
                        result.getPosition(),
                        result.getAcceptedByAthlete(),
                        result.getEvent().getName(),
                        result.getEvent().getDescription(),
                        result.getEvent().getId()
                )).toList();
    }

    public UserEntitySuperDto getLoggedUserInfo() {
        UserEntity user = getLoggedUser();
        List<ResultWithEventDetailsDto> results = getUserResultsWithEventDetails(user);

        return new UserEntitySuperDto(
                user.getId(),
                user.getEmail(),
                user.getUsername(),
                user.getPassword(),
                user.getRole(),
                user.getComments(),
                user.getInscriptions(),
                results
        );
    }

    public UserEntity updateUser(CreateUserDto updatedUser) {
        UserEntity loggedUser = this.getLoggedUser();

        try {
            loggedUser.setEmail(updatedUser.getEmail());
            loggedUser.setUsername(updatedUser.getUsername());
            loggedUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            loggedUser.setRole(updatedUser.getRole());
            return userRepository.save(loggedUser);
        } catch (Exception e) {
            throw new RuntimeException("Error updating user");
        }
    }
}
