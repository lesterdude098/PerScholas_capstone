package com.example.capstone4.servicesImpl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.capstone4.dto.UserEntityDto;
import com.example.capstone4.model.Role;
import com.example.capstone4.model.UserEntity;
import com.example.capstone4.repository.RoleRepository;
import com.example.capstone4.repository.UserEntityRepo;
import com.example.capstone4.services.UserEntityService;


import java.util.List;

@Service

public class UserEntityServiceImpl implements UserEntityService {
    private UserEntityRepo userEntityRepo;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserEntityServiceImpl(UserEntityRepo userEntityRepo,RoleRepository roleRepository, PasswordEncoder passwordEncoder){
    super();
    this.userEntityRepo = userEntityRepo;
    this.roleRepository = roleRepository;
    this.passwordEncoder=passwordEncoder;

}

    @Override
    public void saveUserEntity(UserEntityDto userEntitydto) {
        UserEntity userEntity = new UserEntity();

        userEntity.setName(userEntitydto.getUsername());
        userEntity.setEmail(userEntitydto.getEmail());

        //passsword encryption
        userEntity.setPassword(passwordEncoder.encode(userEntitydto.getPassword()));
        Role role = roleRepository.findByName("ADMIN");
                if (role == null){
                    role = checkRoleExist();
                }
                userEntity.setRoles(Arrays.asList(role));
                userEntityRepo.save(userEntity);

    }
    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ADMIN");
        return roleRepository.save(role);
    }


    @Override
    public UserEntity findUserEntityByEmail(String email) {

        return userEntityRepo.findByEmail(email);
    }

    @Override
    public List<UserEntityDto> findAllUserEntity() {
        List<UserEntity> users = userEntityRepo.findAll();

        return users.stream()
                .map((userEntity) -> mapToUserEntityDto(userEntity))
                .collect(Collectors.toList());
    }
    private UserEntityDto mapToUserEntityDto(UserEntity userEntity){
        UserEntityDto userEntityDto = new UserEntityDto();


        userEntityDto.setUsername(userEntityDto.getUsername());
        userEntityDto.setEmail(userEntity.getEmail());



        return userEntityDto;
    }
}
