package com.example.capstone4.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntityDto {
    private Long id;

    @NotEmpty(message= "Username Should not be empty")
    private String username;

    @NotEmpty(message = "email should not be empty")
    private String email;

    @NotEmpty(message = "Password should not be empty")
    private String password;
}
