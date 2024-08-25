package com.example.moviesdvdrental.Repositories;

import com.example.moviesdvdrental.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(Long id);
    List<User> findByUsernameStartingWith(String username);
    Optional<User> findByRole(String role);
    Optional<User> findByUsername(String username);
    User findUserByUsername(String username);

}
