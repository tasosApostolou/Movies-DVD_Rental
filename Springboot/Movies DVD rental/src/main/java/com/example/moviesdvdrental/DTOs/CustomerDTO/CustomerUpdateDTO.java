package com.example.moviesdvdrental.DTOs.CustomerDTO;

import com.example.moviesdvdrental.DTOs.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CustomerUpdateDTO extends BaseDTO {
    public String firstname;
    public String lastname;
//    public Long userId;

    public CustomerUpdateDTO(Long id,String firstname, String lastname) {
        this.setId(id);
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
