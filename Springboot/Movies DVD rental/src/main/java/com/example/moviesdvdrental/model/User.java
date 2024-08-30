package com.example.moviesdvdrental.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "User")
public class User extends AbstractEntity implements UserDetails {
    @Column(length = 30, unique = true, nullable = false)
    private String username;

    @Column(length = 500)
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private Customer customer;
    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private Employee employee;

    public User(Long id,String username, String password, Role role) {
        this.setId(id);
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(Long id,String username, Role role) {
        this.setId(id);
        this.username = username;
        this.role = role;
    }

    public static User NEW_CUSTOMER(String username, String password) {
        User user = new User();
//        user.setIsActive(true);
        user.setRole(Role.CUSTOMER);
        user.setStatus(Status.APPROVED);
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }

    public static User NEW_EMPLOYEE(String username, String password) {
        User user = new User();
//        user.setIsActive(true);
        user.setRole(Role.EMPLOYEE);
        user.setStatus(Status.APPROVED);
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
//        return UserDetails.super.isAccountNonExpired();
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
//        return UserDetails.super.isAccountNonLocked();
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
//        return UserDetails.super.isCredentialsNonExpired();
        return true;
    }

    @Override
    public boolean isEnabled() {
//        return UserDetails.super.isEnabled();
        return true;
    }
}
