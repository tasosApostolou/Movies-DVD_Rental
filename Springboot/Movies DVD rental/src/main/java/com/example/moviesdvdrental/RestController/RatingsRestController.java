package com.example.moviesdvdrental.RestController;

import com.example.moviesdvdrental.DTOs.RatingsDTO.RatingsInsertDTO;
import com.example.moviesdvdrental.DTOs.RatingsDTO.RatingsReadOnlyDTO;
import com.example.moviesdvdrental.DTOs.RentalDTO.RentalsReadOnlyDTO;
import com.example.moviesdvdrental.Exceptions.EntityNotFoundException;
import com.example.moviesdvdrental.Service.IRatingsService;
import com.example.moviesdvdrental.mapper.Mapper;
import com.example.moviesdvdrental.model.Ratings;
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
@RequestMapping("/api/rating")
@RequiredArgsConstructor
public class RatingsRestController {
    private final IRatingsService ratingsService;

    @Operation(summary = " Add new rating on movie by a customer ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Rating added",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = RatingsReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input was supplied",
                    content = @Content),
            @ApiResponse(responseCode = "503", description = "Service Unavailable",
                    content = @Content)})
    @PostMapping("/add")
    public ResponseEntity<RatingsReadOnlyDTO> addRating(@RequestBody RatingsInsertDTO insertDTO){
        Ratings rating;
        try{
            rating = ratingsService.AddRating(insertDTO);
            RatingsReadOnlyDTO readOnlyDTO = Mapper.mapToReadOnlyDTO(rating);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(readOnlyDTO.getMovie().getId())
                    .toUri();
            return ResponseEntity.created(location).body(readOnlyDTO);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @Operation(summary = "get all ratings on a movie by customer id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "rentals found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = RentalsReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "404", description = "ratings not found",
                    content = @Content)})
    @GetMapping("/movie/{movieID}")
    public ResponseEntity<List<RatingsReadOnlyDTO>> getMovieRatings(@PathVariable ("movieID") Long movieID){
        List<Ratings> ratings;
        List<RatingsReadOnlyDTO> ratingsReadOnlyDTO = new ArrayList<>();
        try{
            ratings = ratingsService.getRatingsByMovieId(movieID);
            ratings.forEach(rating -> ratingsReadOnlyDTO.add(Mapper.mapToReadOnlyDTO(rating)));
        }catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ratingsReadOnlyDTO, HttpStatus.OK);
    }
}
