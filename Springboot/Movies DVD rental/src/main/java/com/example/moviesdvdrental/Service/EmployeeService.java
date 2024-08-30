package com.example.moviesdvdrental.Service;

import com.example.moviesdvdrental.DTOs.CustomerDTO.CustomerRegisterDTO;
import com.example.moviesdvdrental.DTOs.EmployeeDTO.EmployeeRegisterDTO;
import com.example.moviesdvdrental.Exceptions.EntityAlreadyExistsException;
import com.example.moviesdvdrental.Repositories.EmployeeRepository;
import com.example.moviesdvdrental.Repositories.UserRepository;
import com.example.moviesdvdrental.mapper.Mapper;
import com.example.moviesdvdrental.model.Customer;
import com.example.moviesdvdrental.model.Employee;
import com.example.moviesdvdrental.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService implements IEmployeeService{
    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    @Override
    @Transactional
    public Employee registerEmployee(EmployeeRegisterDTO dto) throws EntityAlreadyExistsException {
        Employee employee;
        User user;
        String passwordEncoded = new BCryptPasswordEncoder().encode(dto.getPassword());

        try {
            employee = Mapper.extractEmployeeFromRegisterEmployeeDTO(dto);
            user = Mapper.extractUserFromRegisterDTO(dto);
            user.setPassword(passwordEncoded);
            Optional<User> returnedUser = userRepository.findByUsername(dto.getUsername());
            if (returnedUser.isPresent()) throw new EntityAlreadyExistsException(Employee.class, dto.getUsername());
            employee.addUser(user);
            employeeRepository.save(employee);
            log.info("Employee added");
        } catch (EntityAlreadyExistsException e) {
            log.error("Problem in create user employee" + e.getMessage());
            throw e;
        }
        return employee;
    }
}
