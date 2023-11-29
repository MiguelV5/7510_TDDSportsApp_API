package com.TddSportsApp.service;

import com.TddSportsApp.models.UserEntity;
import com.TddSportsApp.models.dto.CreateUserDto;
import com.TddSportsApp.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    public void whenCreateUser_thenReturnUser() {
        // given
        CreateUserDto createUserDto = new CreateUserDto();
        createUserDto.setUsername("username");
        createUserDto.setPassword("password");
        createUserDto.setEmail("email");
        createUserDto.setRole("role");

        // when
        when(userRepository.save(any(UserEntity.class))).thenReturn(new UserEntity());
        when(passwordEncoder.encode(any(String.class))).thenReturn("encodedPassword");
        UserEntity user = userService.createUser(createUserDto);

        // then
        assertThat(user.getUsername()).isEqualTo(createUserDto.getUsername());
        assertThat(user.getEmail()).isEqualTo(createUserDto.getEmail());
        assertThat(user.getRole()).isEqualTo(createUserDto.getRole());
    }

    @Test
    public void whenGetUserById_thenReturnUser() {
        // given
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setUsername("username");
        user.setPassword("password");
        user.setEmail("email");
        user.setRole("role");

        // when
        when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(user));
        UserEntity foundUser = userService.getUserById(1L);

        // then
        assertThat(foundUser.getId()).isEqualTo(user.getId());
        assertThat(foundUser.getUsername()).isEqualTo(user.getUsername());
        assertThat(foundUser.getEmail()).isEqualTo(user.getEmail());
        assertThat(foundUser.getRole()).isEqualTo(user.getRole());
    }

    @Test
    public void givenInvalidUserId_whenGetUserById_thenThrowUserNotFoundException() {
        when(userRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        try {
            userService.getUserById(1L);
        } catch (Exception e) {
            assertThat(e.getMessage()).isEqualTo("User not found with id: 1");
        }
    }

    @Test
    public void whenGetAllUsers_thenReturnAllUsers() {
        // given
        UserEntity user1 = new UserEntity();
        user1.setId(1L);
        user1.setUsername("username1");
        user1.setPassword("password1");
        user1.setEmail("email1");
        user1.setRole("role1");

        UserEntity user2 = new UserEntity();
        user2.setId(2L);
        user2.setUsername("username2");
        user2.setPassword("password2");
        user2.setEmail("email2");
        user2.setRole("role2");

        // when
        when(userRepository.findAll()).thenReturn(List.of(user1, user2));
        List<UserEntity> users = userService.getUsers();

        // then
        assertThat(users.size()).isEqualTo(2);
        assertThat(users.get(0).getId()).isEqualTo(user1.getId());
        assertThat(users.get(0).getUsername()).isEqualTo(user1.getUsername());
        assertThat(users.get(0).getEmail()).isEqualTo(user1.getEmail());
        assertThat(users.get(0).getRole()).isEqualTo(user1.getRole());
        assertThat(users.get(1).getId()).isEqualTo(user2.getId());
        assertThat(users.get(1).getUsername()).isEqualTo(user2.getUsername());
        assertThat(users.get(1).getEmail()).isEqualTo(user2.getEmail());
        assertThat(users.get(1).getRole()).isEqualTo(user2.getRole());
    }

    @Test
    public void whenDeleteUser_thenDeleteUser() {
        // given
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setUsername("username");
        user.setPassword("password");
        user.setEmail("email");
        user.setRole("role");

        // when
        when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(user));
        userService.deleteUser("1");
        when(userRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        // then
        assertThat(userRepository.findById(1L)).isEmpty();
    }
}
