package com.example.moviesdvdrental.Repositories;

import com.example.moviesdvdrental.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
