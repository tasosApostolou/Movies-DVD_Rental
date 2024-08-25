package com.example.moviesdvdrental.Service;

import com.example.moviesdvdrental.DTOs.UserDTO.UserInsertDTO;
import com.example.moviesdvdrental.DTOs.UserDTO.UserUpdateDTO;
import com.example.moviesdvdrental.Exceptions.EntityNotFoundException;
import com.example.moviesdvdrental.model.User;

import java.util.List;

public interface IUserService {
    User insertUser(UserInsertDTO userDTO) throws Exception;
    User updateUser(UserUpdateDTO userDTO) throws EntityNotFoundException;
    User deleteUser(Long id) throws EntityNotFoundException;
    List<User> getUsersByUsername(String username) throws EntityNotFoundException;
    User getUserById(Long id) throws EntityNotFoundException;
    User getUserByUsername(String username) throws EntityNotFoundException;
}
