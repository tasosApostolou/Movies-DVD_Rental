package com.example.moviesdvdrental.Service;

import com.example.moviesdvdrental.DTOs.CustomerDTO.CustomerRegisterDTO;
import com.example.moviesdvdrental.DTOs.CustomerDTO.CustomerUpdateDTO;
import com.example.moviesdvdrental.Exceptions.EntityAlreadyExistsException;
import com.example.moviesdvdrental.Exceptions.EntityNotFoundException;
import com.example.moviesdvdrental.model.Customer;

import java.util.List;

public interface ICustomerService {
    Customer registerCustomer(CustomerRegisterDTO dto) throws EntityAlreadyExistsException;
    List<Customer> getCustomersByLastname(String lastname) throws EntityNotFoundException;
    Customer getCustomerById(Long id) throws EntityNotFoundException;
    Customer updateCustomer(CustomerUpdateDTO customerDTO) throws EntityNotFoundException;
    Customer deleteCustomer(Long id) throws EntityNotFoundException;
}
