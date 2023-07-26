package com.example.capstone4.repository;


import com.example.capstone4.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepo extends JpaRepository<UserEntity,Long> {
    UserEntity findByEmail(String email);
}
