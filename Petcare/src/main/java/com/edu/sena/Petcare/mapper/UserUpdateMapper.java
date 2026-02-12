package com.edu.sena.Petcare.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


import com.edu.sena.Petcare.dto.UserUpdateDTO;
import com.edu.sena.Petcare.models.User;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserUpdateMapper {
    private final ModelMapper modelMapper;

    public User toEntity(UserUpdateDTO userUpdateDTO){
        return modelMapper.map(userUpdateDTO, User.class);
    }

    public void toEntity(UserUpdateDTO userUpdateDTO, User userExistente){
        modelMapper.map(userUpdateDTO, userExistente);
    }

    public UserUpdateDTO toDTO(User user){
        return modelMapper.map(user, UserUpdateDTO.class);
    }
}