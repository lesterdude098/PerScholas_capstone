package com.example.capstone4.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name="first_name", nullable = false)
    @NotEmpty(message = "First Name is required")
    private String firstName;


    @Column(name="last_name", nullable = false)
    @NotEmpty(message = "Last Name is required")
    private String lastName;

    @Column(name="email", unique = true)

    @NotEmpty(message = "Email")
    @Pattern(regexp ="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}", message = "Invalid Email format" )
    private String email;

//    @ManyToMany
//    @JoinTable(name="customer_project",
//        joinColumns = {@JoinColumn(name = "customer_id", referencedColumnName = "id")},
//            inverseJoinColumns = {@JoinColumn(name = "project_id", referencedColumnName = "id")})

    //one customer with many projects
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)

    public List<Project> projects = new ArrayList<>();




    public Customer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }


}
