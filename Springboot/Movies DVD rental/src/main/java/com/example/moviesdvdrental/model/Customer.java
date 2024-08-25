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
public class Customer extends AbstractEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long Id;
    @Column
    private String firstname;
    @Column
    private String lastname;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
    private Set<Rentals> rentals = new HashSet<>();
    @OneToMany(mappedBy = "customer",cascade = CascadeType.REMOVE)
    private Set<Ratings> ratings = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "user_id",referencedColumnName = "id",unique = true)
    private User user;

    public Customer(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Set<Rentals> getAllCustomerRentals(){return Collections.unmodifiableSet(rentals);
    }

    public void addUser(User user) {
        this.user = user;
        user.setCustomer(this);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + this.getId() + '\'' +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
