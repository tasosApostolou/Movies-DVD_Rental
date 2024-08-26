package com.example.moviesdvdrental.DTOs.LoginDTO;

import com.example.moviesdvdrental.model.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseTokenDTO {
    public Long userId;
    public String username;
    @Enumerated(EnumType.STRING)
    public Role role;
    public Long roleEntityId;

    public LoginResponseTokenDTO(Long userId, String username, Role role, Long roleEntityId) {
        this.userId = userId;
        this.username = username;
        this.role = role;
        this.roleEntityId = roleEntityId;
    }
}
