package com.example.moviesdvdrental.DTOs.UserDTO;

import com.example.moviesdvdrental.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserInsertDTO {
    //    @NotBlank
    private String username;
    //    @NotBlank
    private String password;
    //    @NotBlank
    private String role;
}