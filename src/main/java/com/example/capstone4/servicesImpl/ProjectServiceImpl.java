package com.example.capstone4.servicesImpl;

import com.example.capstone4.model.Project;
import com.example.capstone4.repository.ProjectRepository;
import com.example.capstone4.services.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository){
        this.projectRepository=projectRepository;
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public List<Project> getAllProjectsForCustomer(Long id) {
        return projectRepository.findByCustomerId(id);
    }

    @Override
    public Project editProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public void deleteProjectById(Long id) {
        projectRepository.deleteById(id);
    }

    @Override
    public Object getProjectById(Long id) {
        return projectRepository.findById(id).get();
    }
}
