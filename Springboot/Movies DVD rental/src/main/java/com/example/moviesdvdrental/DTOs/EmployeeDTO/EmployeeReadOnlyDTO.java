package com.example.moviesdvdrental.DTOs.EmployeeDTO;

import com.example.moviesdvdrental.DTOs.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeReadOnlyDTO extends BaseDTO {
    public String firstname;
    public String lastname;
    public String ssn;
    public Long userId;

    public EmployeeReadOnlyDTO(Long id,String firstname, String lastname, String ssn, Long userId) {
        this.setId(id);
        this.firstname = firstname;
        this.lastname = lastname;
        this.ssn=ssn;
        this.userId=userId;
    }
}

