package com.example.moviesdvdrental.RestController;


import com.example.moviesdvdrental.DTOs.RentalDTO.RentalsInsertDTO;
import com.example.moviesdvdrental.DTOs.RentalDTO.RentalsReadOnlyDTO;
import com.example.moviesdvdrental.Exceptions.EntityNotFoundException;
import com.example.moviesdvdrental.Service.IRentalsService;
import com.example.moviesdvdrental.mapper.Mapper;
import com.example.moviesdvdrental.model.Rentals;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rental")
public class RentalsRestController {
    private final IRentalsService rentalsService;

    @Operation(summary = " Add new rental with customer and movie ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Rental added",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = RentalsReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input was supplied",
                    content = @Content),
            @ApiResponse(responseCode = "503", description = "Service Unavailable",
                    content = @Content)})
    @PostMapping("/add")
    public ResponseEntity<RentalsReadOnlyDTO> addRental(@RequestBody RentalsInsertDTO insertDTO){
        Rentals rental;
        try{

            rental = rentalsService.AddNewRental(insertDTO);
            RentalsReadOnlyDTO readOnlyDTO = Mapper.mapToReadOnlyDTO(rental);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(readOnlyDTO.getMovie().getId())
                    .toUri();
            return ResponseEntity.created(location).body(readOnlyDTO);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
    @Operation(summary = "get all rentals of a movie by movie id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "rentals found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = RentalsReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "404", description = "rentals not found",
                    content = @Content)})
    @GetMapping("/movie/{movieID}")
    public ResponseEntity<List<RentalsReadOnlyDTO>> getMovieRentals(@PathVariable("movieID") Long movieID){
        List<Rentals> rentals;
        List<RentalsReadOnlyDTO> rentalsReadOnlyDTO = new ArrayList<>();
        try{
            rentals = rentalsService.getRentalsByMovieId(movieID);
            rentals.forEach(rental -> rentalsReadOnlyDTO.add(Mapper.mapToReadOnlyDTO(rental)));
        }catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(rentalsReadOnlyDTO, HttpStatus.OK);
    }

    @Operation(summary = "get all rentals of a customer by customer id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "rentals found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = RentalsReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "404", description = "rentals not found",
                    content = @Content)})
    @GetMapping("/customer/{customerID}")
    public ResponseEntity<List<RentalsReadOnlyDTO>> getCustomerRentals(@PathVariable("customerID") Long customerID){
        List<Rentals> rentals;
        List<RentalsReadOnlyDTO> rentalsReadOnlyDTO = new ArrayList<>();
        try{
            rentals = rentalsService.getRentalsByCustomerId(customerID);
            rentals.forEach(rental -> rentalsReadOnlyDTO.add(Mapper.mapToReadOnlyDTO(rental)));
        }catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(rentalsReadOnlyDTO, HttpStatus.OK);
    }
}
