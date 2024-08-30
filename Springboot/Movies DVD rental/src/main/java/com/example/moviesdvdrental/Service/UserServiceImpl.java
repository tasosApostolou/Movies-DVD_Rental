package com.example.moviesdvdrental.Service;

import com.example.moviesdvdrental.DTOs.UserDTO.UserInsertDTO;
import com.example.moviesdvdrental.DTOs.UserDTO.UserUpdateDTO;
import com.example.moviesdvdrental.Exceptions.EntityAlreadyExistsException;
import com.example.moviesdvdrental.Exceptions.EntityNotFoundException;
import com.example.moviesdvdrental.Repositories.UserRepository;
import com.example.moviesdvdrental.mapper.Mapper;
import com.example.moviesdvdrental.model.Role;
import com.example.moviesdvdrental.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;

    @Transactional
    @Override
    public User insertUser(UserInsertDTO dto) throws Exception {
        User user;

        try{
            user = Mapper.mapToUser(dto);
            Optional<User> userToCreate = userRepository.findByUsername(user.getUsername());
            if (userToCreate.isPresent()) throw new EntityAlreadyExistsException(User.class,dto.getUsername());
            user.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));
            user = userRepository.save(user);
            if(user.getId()==null){
                throw new Exception("Insert error");
            }
            log.info("insert succes for user with id"+ user.getId());
            return user;
        }catch (Exception e){
            log.error("insert error "+ e.getMessage());
            throw e;
        }
    }

    @Transactional
    @Override
    public User updateUser(UserUpdateDTO dto) throws EntityNotFoundException {
        User user;
        User userToUpdate;
        try {
            user = userRepository.findById(dto.getId()).orElseThrow(() -> new EntityNotFoundException(User.class,dto.getId()));
            userToUpdate = Mapper.mapToUser(dto);// This user without notifications
            userToUpdate.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));
            user = userRepository.save(userToUpdate);
            log.info("User with id: "+ userToUpdate.getId()+ " was updated");
        }catch (EntityNotFoundException e){
            log.error(e.getMessage());
            throw e;
        }
        return user;    }

    @Transactional
    @Override
    public User deleteUser(Long id) throws EntityNotFoundException {
        User user;

        try {
            user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(User.class,id));
            userRepository.deleteById(id);
        }catch (EntityNotFoundException e){
            log.error(e.getMessage());
            throw e;
        }
        return user;
    }

    @Override
    public List<User> getUsersByUsername(String username) throws EntityNotFoundException {
        List<User> users = new ArrayList<>();
        try {
            users = userRepository.findByUsernameStartingWith(username);
            if (users.isEmpty()) throw new EntityNotFoundException(User.class,0L);
            log.info("Users with lastname starting with "+ username +" were found");
        }catch (EntityNotFoundException e){
            log.error(e.getMessage());
            throw e;
        }
        return users;
    }

    @Override
    public User getUserById(Long id) throws EntityNotFoundException {
        User user;
        try {
            user = userRepository.findUserById(id);
            if(user==null)throw new EntityNotFoundException(User.class,id);
        }catch (EntityNotFoundException e){
            log.error(e.getMessage());
            throw e;
        }
        return user;
    }

    @Override
    public User getUserByUsername(String username) throws EntityNotFoundException {
//        User user;
//        try {
//            user = userRepository.findUserByUsername(username);
//            if(user==null)throw new EntityNotFoundException(User.class,0L);
//
//        }catch (EntityNotFoundException e){
//            log.error(e.getMessage());
//            throw e;
//        }

        Optional<User> user = userRepository.findByUsername(username);
        return user.orElseThrow(() -> new EntityNotFoundException(User.class,0L));
    }

    @Override
    @Transactional
    public User promoteToAdmin(Long userId) throws EntityNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException(User.class, userId));
        user.setRole(Role.ADMIN);
        userRepository.save(user);
        return user;
    }

}
