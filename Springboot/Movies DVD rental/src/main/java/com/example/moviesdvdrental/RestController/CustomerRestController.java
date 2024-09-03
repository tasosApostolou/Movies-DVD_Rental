package com.example.moviesdvdrental.RestController;

import com.example.moviesdvdrental.DTOs.CustomerDTO.CustomerReadOnlyDTO;
import com.example.moviesdvdrental.DTOs.CustomerDTO.CustomerRegisterDTO;
import com.example.moviesdvdrental.DTOs.CustomerDTO.CustomerUpdateDTO;
import com.example.moviesdvdrental.Exceptions.EntityAlreadyExistsException;
import com.example.moviesdvdrental.Exceptions.EntityNotFoundException;
import com.example.moviesdvdrental.Service.ICustomerService;
import com.example.moviesdvdrental.Validators.CustomerRegisterValidator;
import com.example.moviesdvdrental.Validators.CustomerUpdateValidator;
import com.example.moviesdvdrental.mapper.Mapper;
import com.example.moviesdvdrental.model.Customer;
import com.example.moviesdvdrental.model.Role;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerRestController {
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
    @PostMapping("/register")
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

    @Operation(summary = "Get customers by their lastname starting with initials")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users Found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid username supplied, Bad-Request",
                    content = @Content)})
    @GetMapping("/customers")
    public ResponseEntity<List<CustomerReadOnlyDTO>> getCustomersByLastnameStarting(@RequestParam("lastname") String lastname) {
        List<Customer> customers;
        List<CustomerReadOnlyDTO> readOnlyDTOS = new ArrayList<>();
        try {
            customers = customerService.getCustomersByLastname(lastname);
            customers.forEach(customer -> readOnlyDTOS.add(Mapper.mapToReadOnlyDTO(customer)));
            return new ResponseEntity<>(readOnlyDTOS, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Get a Customer by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer Found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Customer not found",
                    content = @Content)})
    @GetMapping("/{customerID}")
    public ResponseEntity<CustomerReadOnlyDTO> getCustomer(@PathVariable("customerID") Long id) {
        Customer customer;
        try {
            customer = customerService.getCustomerById(id);
            CustomerReadOnlyDTO dto = Mapper.mapToReadOnlyDTO(customer);
            return ResponseEntity.ok(dto);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Delete a customer by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer Deleted",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerReadOnlyDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Customer not found",
                    content = @Content)})
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerReadOnlyDTO> deleteCustomer(@PathVariable("id") Long id){
        Customer customer;
        try {
            customer = customerService.deleteCustomer(id);
            CustomerReadOnlyDTO readOnlyDTO = Mapper.mapToReadOnlyDTO(customer);
            return new ResponseEntity<>(readOnlyDTO,HttpStatus.OK);
        }catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Update a Customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerReadOnlyDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized user",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input was supplied",
                    content = @Content)})
    @PutMapping("/update/{id}")
    public ResponseEntity<CustomerReadOnlyDTO> updateCustomer(@PathVariable("id") Long id, @RequestBody @Schema(implementation = CustomerUpdateDTO.class) CustomerUpdateDTO dto, BindingResult bindingResult) {
        if(!(dto.getId() ==id)){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        updateValidator.validate(dto, bindingResult);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            Customer customer = customerService.updateCustomer(dto);
            CustomerReadOnlyDTO readOnlyDTO = Mapper.mapToReadOnlyDTO(customer);
            return new ResponseEntity<>(readOnlyDTO,HttpStatus.OK);
        }catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
