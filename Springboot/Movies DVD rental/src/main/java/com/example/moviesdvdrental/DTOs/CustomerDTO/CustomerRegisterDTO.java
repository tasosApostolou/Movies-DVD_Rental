package com.example.moviesdvdrental.DTOs.CustomerDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerRegisterDTO {
    private String username;
    private String password;
    private String firstname;
    private String lastname;
}