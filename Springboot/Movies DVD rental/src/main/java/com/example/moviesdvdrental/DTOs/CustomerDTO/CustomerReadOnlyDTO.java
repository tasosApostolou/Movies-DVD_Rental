package com.example.moviesdvdrental.DTOs.CustomerDTO;

import com.example.moviesdvdrental.DTOs.BaseDTO;
import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerReadOnlyDTO extends BaseDTO {
    public String firstname;
    public String lastname;
    public Long userId;

    public CustomerReadOnlyDTO(Long id,String firstname, String lastname, Long userId) {
        this.setId(id);
        this.firstname = firstname;
        this.lastname = lastname;
        this.userId=userId;
    }
}
