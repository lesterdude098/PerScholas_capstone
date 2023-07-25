package com.example.capstone4.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotEmpty(message= "Required")
    private String projectType;
    @NotNull
    @NotEmpty(message = "Short description Required")
    private String projectDesc;

    //many projects to one customer
    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

}
