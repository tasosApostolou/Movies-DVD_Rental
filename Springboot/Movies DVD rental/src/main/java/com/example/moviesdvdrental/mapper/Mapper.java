package com.example.moviesdvdrental.mapper;

import com.example.moviesdvdrental.DTOs.CategoryDTO.CategoryInsertDTO;
import com.example.moviesdvdrental.DTOs.CategoryDTO.CategoryReadOnlyDTO;
import com.example.moviesdvdrental.DTOs.CustomerDTO.CustomerReadOnlyDTO;
import com.example.moviesdvdrental.DTOs.CustomerDTO.CustomerRegisterDTO;
import com.example.moviesdvdrental.DTOs.CustomerDTO.CustomerUpdateDTO;
import com.example.moviesdvdrental.DTOs.DirectorDTO.DirectorInsertDTO;
import com.example.moviesdvdrental.DTOs.DirectorDTO.DirectorReadOnlyDTO;
import com.example.moviesdvdrental.DTOs.EmployeeDTO.EmployeeReadOnlyDTO;
import com.example.moviesdvdrental.DTOs.EmployeeDTO.EmployeeRegisterDTO;
import com.example.moviesdvdrental.DTOs.LoginDTO.LoginResponseTokenDTO;
import com.example.moviesdvdrental.DTOs.MoviesDTO.MoviesInsertDTO;
import com.example.moviesdvdrental.DTOs.MoviesDTO.MoviesReadOnlyDTO;
import com.example.moviesdvdrental.DTOs.RatingsDTO.RatingsReadOnlyDTO;
import com.example.moviesdvdrental.DTOs.RentalDTO.RentalsReadOnlyDTO;
import com.example.moviesdvdrental.DTOs.UserDTO.UserInsertDTO;
import com.example.moviesdvdrental.DTOs.UserDTO.UserReadOnlyDTO;
import com.example.moviesdvdrental.DTOs.UserDTO.UserUpdateDTO;
import com.example.moviesdvdrental.DTOs.actorDTO.ActorInsertDTO;
import com.example.moviesdvdrental.DTOs.actorDTO.ActorReadOnlyDTO;
import com.example.moviesdvdrental.model.*;

import java.util.List;
import java.util.stream.Collectors;

public class Mapper {
    public static Actor mapToActor(ActorInsertDTO dto) {
        return new Actor(dto.getFirstname(), dto.getLastname());
    }

    public static Director mapToDirector(DirectorInsertDTO dto) {
        return new Director(dto.getFirstname(), dto.getLastname());
    }

    public static Movies mapToMovie(MoviesInsertDTO dto) {
        System.out.println(dto.getCountCopies()+"dsfadfadfdsaafdsafdsafsdasgf");
        return new Movies(dto.getTitle(), dto.getYear(), dto.getCountCopies(), new Director(dto.getDirector().getFirstname(), dto.getDirector().getLastname()));
    }

    public static Category mapToCategory(CategoryInsertDTO dto) {
        return new Category(dto.getCategoryName());
    }

    public static Customer extractCustomerFromRegisterCustomerDTO(CustomerRegisterDTO dto) {
        return new Customer(dto.getFirstname(), dto.getLastname());
    }

    public static User extractUserFromRegisterDTO(CustomerRegisterDTO dto) {
        return User.NEW_CUSTOMER(dto.getUsername(), dto.getPassword());
    }
    public static User extractUserFromRegisterDTO(EmployeeRegisterDTO dto) {
        return User.NEW_CUSTOMER(dto.getUsername(), dto.getPassword());
    }
    public static Employee extractEmployeeFromRegisterEmployeeDTO(EmployeeRegisterDTO dto) {
        return new Employee(dto.getFirstname(),dto.getLastname(),dto.getSsn());
    }

    public static Customer mapToCustomer(CustomerUpdateDTO customerDTO) {
        Customer customer = new Customer(customerDTO.getFirstname(), customerDTO.getLastname());
        customer.setId(customerDTO.getId());
        return customer;
    }

    public static User mapToUser(UserInsertDTO dto) {
        return new User(null, dto.getUsername(), dto.getPassword(), Role.valueOf(dto.getRole()));
    }

    public static ActorReadOnlyDTO mapToReadOnlyDTO(Actor actor) {
        ActorReadOnlyDTO actorDTO = new ActorReadOnlyDTO(actor.getId(), actor.getFirstname(), actor.getLastname());
        return actorDTO;
    }

    public static DirectorReadOnlyDTO mapToReadOnlyDTO(Director director) {
        DirectorReadOnlyDTO directorDTO = new DirectorReadOnlyDTO(director.getId(), director.getFirstname(), director.getLastname());
        return directorDTO;
    }

    public static CategoryReadOnlyDTO mapToReadOnlyDTO(Category category) {
        CategoryReadOnlyDTO categoryDTO = new CategoryReadOnlyDTO(category.getId(), category.getCategoryName());
        return categoryDTO;
    }

    public static MoviesReadOnlyDTO mapToReadOnlyDTO(Movies movie) {
        MoviesReadOnlyDTO movieDTO = new MoviesReadOnlyDTO(movie.getId(), movie.getTitle(), movie.getYear(), movie.getCountCopies(), mapToReadOnlyDTO(movie.getDirector()) );
//        movieDTO.setActors(movie.getAllActors().stream().map(Mapper::mapToReadOnlyDTO).collect(Collectors.toList()));
        movie.getAllActors().forEach(actor -> movieDTO.getActors().add(mapToReadOnlyDTO(actor)));
        movie.getAllCategories().forEach(category -> movieDTO.getCategories().add(mapToReadOnlyDTO(category)));
        return movieDTO;
    }

    public static RatingsReadOnlyDTO mapToReadOnlyDTO(Ratings rating) {
        RatingsReadOnlyDTO ratingDTO = new RatingsReadOnlyDTO(mapToReadOnlyDTO(rating.getMovie()), mapToReadOnlyDTO(rating.getCustomer()), rating.getRating());
        return ratingDTO;
    }

    public static RentalsReadOnlyDTO mapToReadOnlyDTO(Rentals rental) {
        RentalsReadOnlyDTO rentalDTO = new RentalsReadOnlyDTO(mapToReadOnlyDTO(rental.getMovie()), mapToReadOnlyDTO(rental.getCustomer()), rental.getPrice(),rental.getStatus());
        return rentalDTO;
    }

    public static CustomerReadOnlyDTO mapToReadOnlyDTO(Customer customer) {
        CustomerReadOnlyDTO readOnlyDTO = new CustomerReadOnlyDTO(customer.getId(), customer.getFirstname(), customer.getLastname(), customer.getUser().getId());
        return readOnlyDTO;
    }
    public static EmployeeReadOnlyDTO mapToReadOnlyDTO(Employee employee) {
        EmployeeReadOnlyDTO readOnlyDTO = new EmployeeReadOnlyDTO(employee.getId(),employee.getFirstname(),employee.getLastname(),employee.getSsn(),employee.getUser().getId());
        return readOnlyDTO;
    }

    public static User mapToUser(UserUpdateDTO dto) {
        return new User(dto.getId(), dto.getUsername(), dto.getPassword(), dto.getRole());
    }

    public static UserReadOnlyDTO mapToReadOnlyDTO(User user) {
        return new UserReadOnlyDTO(user.getId(), user.getUsername(), String.valueOf(user.getRole()));
    }

    public static LoginResponseTokenDTO mapToLoginResponseDTO(User user) {
        if (user.getRole() == Role.CUSTOMER) {
            return new LoginResponseTokenDTO(user.getId(), user.getUsername(), Role.CUSTOMER, user.getCustomer().getId());
        } else {
            return new LoginResponseTokenDTO(user.getId(), user.getUsername(), Role.EMPLOYEE, user.getEmployee().getId());
        }
    }
}