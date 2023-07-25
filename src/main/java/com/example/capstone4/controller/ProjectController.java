package com.example.capstone4.controller;

import com.example.capstone4.model.Customer;
import com.example.capstone4.model.Project;
import com.example.capstone4.repository.CustomerRepository;
import com.example.capstone4.repository.ProjectRepository;
import com.example.capstone4.services.ProjectService;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Data
@Controller
public class ProjectController {
    private ProjectService projectService;
    private final ProjectRepository projectRepository;
    private final CustomerRepository customerRepository;


    public ProjectController(ProjectService projectService, ProjectRepository projectRepository,
                             CustomerRepository customerRepository) {
        this.projectService = projectService;
        this.projectRepository = projectRepository;
        this.customerRepository = customerRepository;
    }

    //view projects
    @GetMapping("/customers/{id}/projects")
    public String listProjects(@PathVariable Long id, Model model) {
        Customer customer = customerRepository.findById(id).orElse(null);
//        List<Project> projects = projectService.getAllProjectsForCustomer(id);
        if (customer != null) {
            List<Project> projects = projectRepository.findByCustomer(customer);
            model.addAttribute("customer", customer);
            model.addAttribute("projects", projects);

            return "customer_project";
        }
        return "redirect:/customers";

    }



    //create-add new projects
    @GetMapping("/customers/{id}/addprojects")
    public String addCustomerProjectForm(@PathVariable Long id, Model model) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            Project project = new Project();
            project.setCustomer(customer);
            model.addAttribute("project", project);
            return "add_projects";
        } else {
            return "redirect:/customers";
        }

    }

    // create new project for customer
    @PostMapping("/customers/project/save/{id}")
    public String saveProject(@PathVariable("id") Long id, @ModelAttribute("project") @Valid Project project, BindingResult result) {
        Customer customer = customerRepository.findById(id).orElse(null);
        System.out.print("in saveProject");
        if (result.hasErrors()) {
            return "add_projects";
        }
        if (customer != null) {
            project.setCustomer(customer);
            projectRepository.save(project);
        } else {
            projectRepository.save(project);
            return "redirect:/customers";
        }
        return "redirect:/customers";
    }

    //Update Project
    @GetMapping("/customers/edit/project/{id}")
    public String editProjectForm(@PathVariable Long id, Model model){
        model.addAttribute("project", projectService.getProjectById(id));
        return "edit_project";
    }

    //handler for update project
    @PostMapping("/customers/projects/{id}")
    public String updateProject(@PathVariable Long id, @ModelAttribute("project") Project project, Model model){
        //from mysSQL
        Project currentProject= (Project) projectService.getProjectById(id);
        currentProject.setId(id);
        currentProject.setProjectType(project.getProjectType());
        currentProject.setProjectDesc(project.getProjectDesc());

//save
        projectService.editProject(currentProject);
        return "redirect:/customers";

    }

    //delete project
    @GetMapping("/customers/projects/delete/{id}")
    public String deleteProject(@PathVariable Long id){
        projectService.deleteProjectById(id);
        return "redirect:/customers";
    }


}
