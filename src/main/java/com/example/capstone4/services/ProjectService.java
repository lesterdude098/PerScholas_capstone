package com.example.capstone4.services;

import com.example.capstone4.model.Project;

import java.util.List;

public interface ProjectService{
    List<Project> getAllProjects();

    Project saveProject(Project project);
    List<Project> getAllProjectsForCustomer(Long id);

    Project editProject(Project project);
    void deleteProjectById(Long id);

    Object getProjectById(Long id);
}
