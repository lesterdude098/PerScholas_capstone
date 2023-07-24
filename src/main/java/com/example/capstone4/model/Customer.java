package com.example.capstone4.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "required")
    @Column(name="first_name", nullable = false)
    private String firstName;

    @NotEmpty(message = "required")
    @Column(name="last_name", nullable = false)
    private String lastName;

    @NotEmpty(message = "required")
    @Column(name="email", unique = true)
    private String email;

    public Customer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
