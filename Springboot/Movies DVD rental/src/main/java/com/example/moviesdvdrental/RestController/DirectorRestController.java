package com.example.moviesdvdrental.RestController;

import com.example.moviesdvdrental.DTOs.DirectorDTO.DirectorInsertDTO;
import com.example.moviesdvdrental.DTOs.DirectorDTO.DirectorReadOnlyDTO;
import com.example.moviesdvdrental.Exceptions.EntityNotFoundException;
import com.example.moviesdvdrental.Service.IDirectorService;
import com.example.moviesdvdrental.Validators.DirectorInsertValidator;
import com.example.moviesdvdrental.mapper.Mapper;
import com.example.moviesdvdrental.model.Director;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/director")
public class DirectorRestController {
    private final IDirectorService directorService;
    private final DirectorInsertValidator directorInsertValidator;
    @Operation(summary = "Get directors by their lastname starting with initials")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Directors Found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DirectorReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid lastname supplied",
                    content = @Content)})
    @GetMapping("/directors")
    public ResponseEntity<List<DirectorReadOnlyDTO>> getDirectorsByLastname(@RequestParam("lastname") String lastname) {
        List<Director> directors;
        System.out.println(lastname);
        List<DirectorReadOnlyDTO> readOnlyDTOs = new ArrayList<>(); //the returned list

        try {
            directors = directorService.getDirectorsByLastname(lastname);
//            for (Director director : directors) {
//                readOnlyDTOs.add(Mapper.mapToReadOnlyDTO(director)); // convert from DTO to model class Director
//            }
            directors.forEach(director -> readOnlyDTOs.add(Mapper.mapToReadOnlyDTO(director)));
            return new ResponseEntity<>(readOnlyDTOs, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
//            throw e;
        }
    }
    @Operation(summary = "Get a Director by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Director Found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DirectorReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Director not found",
                    content = @Content)})
    @GetMapping("/{directorID}")
    public ResponseEntity<DirectorReadOnlyDTO> getDirector(@PathVariable("directorID") Long id) {
        Director director;

        try {
            director = directorService.getDirectorById(id);
            DirectorReadOnlyDTO dto = Mapper.mapToReadOnlyDTO(director);
            return ResponseEntity.ok(dto);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    //
    @Operation(summary = "Add a Director")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Director created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DirectorReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input was supplied",
                    content = @Content),
            @ApiResponse(responseCode = "503", description = "Service Unavailable",
                    content = @Content)})
    @PostMapping("/add")
    public ResponseEntity<DirectorReadOnlyDTO> addDirector(@Valid @RequestBody DirectorInsertDTO dto, BindingResult bindingResult) {
        directorInsertValidator.validate(dto, bindingResult);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            Director director = directorService.insert(dto);
            DirectorReadOnlyDTO directorReadOnlyDTO = Mapper.mapToReadOnlyDTO(director);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(directorReadOnlyDTO.getId())
                    .toUri();
            return ResponseEntity.created(location).body(directorReadOnlyDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @Operation(summary = "Get all directors ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Directors Found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DirectorReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid lastname supplied",
                    content = @Content)})
    @GetMapping("/get")
    public ResponseEntity<List<DirectorReadOnlyDTO>> getAllDirectors() {
        List<Director> directors;
        List<DirectorReadOnlyDTO> readOnlyDTOs = new ArrayList<>(); //the returned list

        try {
            directors = directorService.getAllDirectors();
            directors.forEach(director -> readOnlyDTOs.add(Mapper.mapToReadOnlyDTO(director)));
            return new ResponseEntity<>(readOnlyDTOs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
//            throw e;
        }
    }
}
