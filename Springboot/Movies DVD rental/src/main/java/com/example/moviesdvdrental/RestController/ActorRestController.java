package com.example.moviesdvdrental.RestController;

import com.example.moviesdvdrental.DTOs.actorDTO.ActorInsertDTO;
import com.example.moviesdvdrental.DTOs.actorDTO.ActorReadOnlyDTO;
import com.example.moviesdvdrental.Exceptions.EntityNotFoundException;
import com.example.moviesdvdrental.Service.IActorService;
import com.example.moviesdvdrental.Validators.ActorInsertValidator;
import com.example.moviesdvdrental.mapper.Mapper;
import com.example.moviesdvdrental.model.Actor;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/actor")
@RequiredArgsConstructor
public class ActorRestController {

        private final IActorService actorService;
        private final ActorInsertValidator actorInsertValidator;
        @Operation(summary = "Get actors by their lastname starting with initials")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Actors Found",
                        content = {@Content(mediaType = "application/json",
                                schema = @Schema(implementation = ActorReadOnlyDTO.class))}),
                @ApiResponse(responseCode = "400", description = "Invalid lastname supplied",
                        content = @Content)})
        @GetMapping("/actors")
        public ResponseEntity<List<ActorReadOnlyDTO>> getActorsByLastname(@RequestParam("lastname") String lastname) {
            List<Actor> actors;
            System.out.println(lastname);
            List<ActorReadOnlyDTO> readOnlyDTOs = new ArrayList<>();

            try {
                actors = actorService.getActorsByLastname(lastname);

                for (Actor actor : actors) {
                    readOnlyDTOs.add(Mapper.mapToReadOnlyDTO(actor));
                }
//if (readOnlyDTOs.isEmpty()) return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
                return new ResponseEntity<>(readOnlyDTOs, HttpStatus.OK);
            } catch (EntityNotFoundException e) {
                return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
//            throw e;
            }
        }
        @Operation(summary = "Get a Actor by id")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Actor Found",
                        content = {@Content(mediaType = "application/json",
                                schema = @Schema(implementation = ActorReadOnlyDTO.class))}),
                @ApiResponse(responseCode = "404", description = "Actor not found",
                        content = @Content)})
        @GetMapping("/{actorID}")
        public ResponseEntity<ActorReadOnlyDTO> getActor(@PathVariable("actorID") Long id) {
            Actor actor;

            try {
                actor = actorService.getActorById(id);
                ActorReadOnlyDTO dto = Mapper.mapToReadOnlyDTO(actor);
                return ResponseEntity.ok(dto);

            } catch (EntityNotFoundException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
//
        @Operation(summary = "Add an actor")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "201", description = "Author created",
                        content = {@Content(mediaType = "application/json",
                                schema = @Schema(implementation = ActorReadOnlyDTO.class))}),
                @ApiResponse(responseCode = "400", description = "Invalid input was supplied",
                        content = @Content),
                @ApiResponse(responseCode = "503", description = "Service Unavailable",
                        content = @Content)})
        @PostMapping("/add")
        public ResponseEntity<ActorReadOnlyDTO> addActor(@Valid @RequestBody ActorInsertDTO dto, BindingResult bindingResult) {
            actorInsertValidator.validate(dto, bindingResult);
            if (bindingResult.hasErrors()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            try {
                Actor actor = actorService.insert(dto);
                ActorReadOnlyDTO actorReadOnlyDTO = Mapper.mapToReadOnlyDTO(actor);
                URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(actorReadOnlyDTO.getId())
                        .toUri();
                return ResponseEntity.created(location).body(actorReadOnlyDTO);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
            }
        }

    @Operation(summary = "Get all actors")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Actors Found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ActorReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "400", description = "no actors",
                    content = @Content)})
    @GetMapping("/get")
    public ResponseEntity<List<ActorReadOnlyDTO>> getAllActors() {
        List<Actor> actors;
        List<ActorReadOnlyDTO> readOnlyDTOs = new ArrayList<>();

        try {
            actors = actorService.getAllActors();
            actors.forEach(actor -> readOnlyDTOs.add(Mapper.mapToReadOnlyDTO(actor)));
            return new ResponseEntity<>(readOnlyDTOs, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
    }
}
