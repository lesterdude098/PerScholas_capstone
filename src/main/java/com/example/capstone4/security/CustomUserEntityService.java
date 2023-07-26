package com.example.capstone4.security;


import com.example.capstone4.model.UserEntity;
import com.example.capstone4.repository.UserEntityRepo;
import com.example.capstone4.services.CustomerService;
import com.example.capstone4.services.UserEntityService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
@Service

public class CustomUserEntityService implements UserDetailsService {

    private UserEntityRepo userEntityRepo;
    public CustomUserEntityService(UserEntityRepo userEntityRepo){
        super();

        this.userEntityRepo = userEntityRepo;
    }


    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        UserEntity user = userEntityRepo.findByEmail(usernameOrEmail);
        if(user !=null){
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                    user.getRoles().stream()
                            .map((role)-> new SimpleGrantedAuthority(role.getName()))
                            .collect(Collectors.toList()));

        } else {
            throw new UsernameNotFoundException("Invalid email/username or password");
        }
    }
}
