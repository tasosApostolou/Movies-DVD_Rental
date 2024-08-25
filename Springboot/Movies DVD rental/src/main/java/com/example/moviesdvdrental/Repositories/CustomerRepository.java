package com.example.moviesdvdrental.Repositories;

import com.example.moviesdvdrental.model.Customer;
import com.example.moviesdvdrental.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findCustomerById(Long id);
    List<Customer> findByLastnameStartingWith(String lastname);

}
