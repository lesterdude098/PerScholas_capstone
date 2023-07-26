package com.example.capstone4.controller;

import com.example.capstone4.dto.UserEntityDto;
import com.example.capstone4.model.UserEntity;
import com.example.capstone4.services.UserEntityService;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.lang.module.ModuleDescriptor;
import java.util.List;
@Data
@Controller
public class UserEntityController {
    private UserEntityService userEntityService;

    @Autowired
    public UserEntityController(UserEntityService userEntityService){
        this.userEntityService = userEntityService;
    }

    //handler
    @GetMapping("/home")
        public String home(){
            return "home";
        }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String ShowRegistrationForm(Model model){
        UserEntityDto user = new UserEntityDto();
        model.addAttribute("userEntity", user);

        return "register";
    }

    //Post
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("userEntity") UserEntityDto userEntityDto, BindingResult bindingResult, Model model){

        UserEntity existingUser = userEntityService.findUserEntityByEmail(userEntityDto.getEmail());



        if (existingUser != null && existingUser.getEmail() !=null && !existingUser.getEmail().isEmpty()){
            bindingResult.rejectValue("email", null, "there is already an account registered with the same email");
        }
        if(bindingResult.hasErrors()){
            model.addAttribute("userEntity", userEntityDto);
            return"/register";

        }

        userEntityService.saveUserEntity(userEntityDto);
        return "redirect:/register?success";
    }

    @GetMapping("/users")
    public String users(Model model){
        List<UserEntityDto> users = userEntityService.findAllUserEntity();
        model.addAttribute("userEntity", users);
        return "users";
    }

}
