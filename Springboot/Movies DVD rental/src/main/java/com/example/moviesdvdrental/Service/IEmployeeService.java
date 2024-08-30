package com.example.moviesdvdrental.Service;

import com.example.moviesdvdrental.DTOs.EmployeeDTO.EmployeeRegisterDTO;
import com.example.moviesdvdrental.Exceptions.EntityAlreadyExistsException;
import com.example.moviesdvdrental.model.Employee;

public interface IEmployeeService {
    Employee registerEmployee(EmployeeRegisterDTO dto) throws EntityAlreadyExistsException;

}
