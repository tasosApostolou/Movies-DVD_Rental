package com.example.moviesdvdrental.DTOs.EmployeeDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeRegisterDTO {
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String ssn;

    public EmployeeRegisterDTO(String firstname, String lastname, String ssn) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.ssn = ssn;
    }
}