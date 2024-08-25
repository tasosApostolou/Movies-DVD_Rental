package com.example.moviesdvdrental.DTOs.UserDTO;

import com.example.moviesdvdrental.DTOs.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserReadOnlyDTO extends BaseDTO {
    private String username;
    //    @Enumerated(EnumType.STRING)
    private String role;


    public UserReadOnlyDTO(Long id, String username, String role) {
        this.setId(id);
        this.username = username;
        this.role = role;
    }
}