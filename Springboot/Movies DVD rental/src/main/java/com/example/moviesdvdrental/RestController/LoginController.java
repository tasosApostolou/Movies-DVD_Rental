package com.example.moviesdvdrental.RestController;

import com.example.moviesdvdrental.Configuration.UserDetailsServiceImpl;
import com.example.moviesdvdrental.DTOs.AuthendicationTokenDTO.AuthendicationResponseDTO;
import com.example.moviesdvdrental.DTOs.LoginDTO.LoginCredentialsDTO;
import com.example.moviesdvdrental.DTOs.LoginDTO.LoginResponseTokenDTO;
import com.example.moviesdvdrental.DTOs.UserDTO.UserReadOnlyDTO;
import com.example.moviesdvdrental.Exceptions.EntityNotFoundException;
import com.example.moviesdvdrental.JwtToken.JwtUtil;
import com.example.moviesdvdrental.Service.IUserService;
import com.example.moviesdvdrental.mapper.Mapper;
import com.example.moviesdvdrental.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LoginController {
    private final IUserService userService;
    //    private final UserInsertValidator insertValidator;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;


    @Operation(summary = "login with credentials")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User logged in",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Invalid password unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content)})
    @PostMapping("/login")
    public AuthendicationResponseDTO loginUser(@RequestBody LoginCredentialsDTO dto, HttpServletResponse response) throws BadCredentialsException, DisabledException, UsernameNotFoundException, IOException {

        User user;
        LoginResponseTokenDTO responseDTO;
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
            user = userService.getUserByUsername(dto.username);
            responseDTO = Mapper.mapToLoginResponseDTO(user);
        } catch (BadCredentialsException | EntityNotFoundException e) {
            throw new BadCredentialsException("Incorrect username or password!");
        } catch (DisabledException disabledException) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User is not activated");
            return null;
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(dto.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails.getUsername(),responseDTO.getUserId(), String.valueOf(responseDTO.getRole()),responseDTO.getRoleEntityId());

        return new AuthendicationResponseDTO(jwt);

    }

}
