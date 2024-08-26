package com.example.moviesdvdrental.DTOs.LoginDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginCredentialsDTO {
    @NotBlank
    public String username;
    @NotBlank
    public String password;
}