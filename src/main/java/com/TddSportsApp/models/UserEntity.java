package com.TddSportsApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Email
    @NotBlank
    @Size(max = 80)
    @Column(unique = true)
    private String email;

    @NotBlank
    @Size(max = 80)
    @Column(unique = true)
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    @Size(max = 5)
    private String role;
}
