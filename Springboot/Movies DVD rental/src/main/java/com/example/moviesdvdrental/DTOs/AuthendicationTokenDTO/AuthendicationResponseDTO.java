package com.example.moviesdvdrental.DTOs.AuthendicationTokenDTO;

import lombok.Getter;

@Getter
public class AuthendicationResponseDTO {
    private final String jwt;
    public AuthendicationResponseDTO(String jwt) {
        this.jwt = jwt;
    }

}