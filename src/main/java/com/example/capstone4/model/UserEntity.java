package com.example.capstone4.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name= "user_roles", joinColumns = @JoinColumn(name="user_id",referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name="role_id",referencedColumnName = "id"))
    private List<Role> roles = new ArrayList<>();


}
