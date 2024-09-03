package com.example.moviesdvdrental.RestController;

import com.example.moviesdvdrental.DTOs.CustomerDTO.CustomerReadOnlyDTO;
import com.example.moviesdvdrental.DTOs.CustomerDTO.CustomerRegisterDTO;
import com.example.moviesdvdrental.DTOs.EmployeeDTO.EmployeeReadOnlyDTO;
import com.example.moviesdvdrental.DTOs.EmployeeDTO.EmployeeRegisterDTO;
import com.example.moviesdvdrental.Exceptions.EntityAlreadyExistsException;
import com.example.moviesdvdrental.Service.ICustomerService;
import com.example.moviesdvdrental.Service.IEmployeeService;
import com.example.moviesdvdrental.Validators.CustomerRegisterValidator;
import com.example.moviesdvdrental.Validators.CustomerUpdateValidator;
import com.example.moviesdvdrental.Validators.EmployeeRegisterValidator;
import com.example.moviesdvdrental.mapper.Mapper;
import com.example.moviesdvdrental.model.Customer;
import com.example.moviesdvdrental.model.Employee;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/register")
@RequiredArgsConstructor
public class RegisterRestController {
    private final ICustomerService customerService;
    private final CustomerRegisterValidator registerCustomerValidator;
    private final CustomerUpdateValidator updateValidator;

    @Operation(summary = "Register a customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Customer created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input was supplied",
                    content = @Content),
            @ApiResponse(responseCode = "503", description = "Service Unavailable",
                    content = @Content)})
    @PostMapping("/customer")
    public ResponseEntity<CustomerReadOnlyDTO> CustomerRegister(@Valid @RequestBody @Schema(implementation = CustomerRegisterDTO.class) CustomerRegisterDTO dto, BindingResult bindingResult) throws EntityAlreadyExistsException {
        registerCustomerValidator.validate(dto,bindingResult);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Customer createdCustomer;
        try {
            createdCustomer = customerService.registerCustomer(dto);
            CustomerReadOnlyDTO customerReadonlyDTO = Mapper.mapToReadOnlyDTO(createdCustomer);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(customerReadonlyDTO.getId())
                    .toUri();
            return ResponseEntity.created(location).body(customerReadonlyDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    private final IEmployeeService employeeService;
    private final EmployeeRegisterValidator registerEmployeeValidator;


    @Operation(summary = "Register an employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Employee registered successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input was supplied",
                    content = @Content),
            @ApiResponse(responseCode = "503", description = "Service Unavailable",
                    content = @Content)})
    @PostMapping("/employee")
    public ResponseEntity<EmployeeReadOnlyDTO> EmployeeRegister(@Valid @RequestBody @Schema(implementation = EmployeeRegisterDTO.class) EmployeeRegisterDTO dto, BindingResult bindingResult) throws EntityAlreadyExistsException {
        registerEmployeeValidator.validate(dto,bindingResult);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Employee createdEmployee;
        try {
            createdEmployee = employeeService.registerEmployee(dto);
            EmployeeReadOnlyDTO employeeReadOnlyDTO = Mapper.mapToReadOnlyDTO(createdEmployee);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(employeeReadOnlyDTO.getId())
                    .toUri();
            return ResponseEntity.created(location).body(employeeReadOnlyDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
