package com.example.capstone4.repository;

import com.example.capstone4.model.Customer;
import com.example.capstone4.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByCustomerId(Long id);

    List<Project> findByCustomer(Customer customer);
}
