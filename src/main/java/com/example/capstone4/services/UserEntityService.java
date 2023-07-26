package com.example.capstone4.services;

import com.example.capstone4.dto.UserEntityDto;
import com.example.capstone4.model.UserEntity;

import java.util.List;

public interface UserEntityService {
    void saveUserEntity(UserEntityDto userEntitydto);
    UserEntity findUserEntityByEmail (String email);
    List<UserEntityDto> findAllUserEntity();
}
