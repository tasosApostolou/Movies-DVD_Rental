package com.example.moviesdvdrental.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Customer")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Employee extends AbstractEntity {
    @Column
    private String firstname;
    @Column
    private String lastname;
    @Column
    private String ssn;

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "user_id",referencedColumnName = "id",unique = true)
    private User user;

    public Employee(String firstname, String lastname,String ssn) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.ssn = ssn;
    }

    public void addUser(User user) {
        this.user = user;
        user.setEmployee(this);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + this.getId() + '\'' +
                "SSN='" + ssn + '\'' +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}

