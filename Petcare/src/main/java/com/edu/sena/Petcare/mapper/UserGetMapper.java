package com.edu.sena.Petcare.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.edu.sena.Petcare.dto.UserGetDTO;
import com.edu.sena.Petcare.models.User;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserGetMapper {
    
    private final ModelMapper modelMapper;

    public User toEntity(UserGetDTO userGetDTO){
        return modelMapper.map(userGetDTO, User.class);
    }

    public void toEntity(UserGetDTO userGetDTO, User userExistente){
        modelMapper.map(userGetDTO, userExistente);
    }

    public UserGetDTO toDTO(User user){
        return modelMapper.map(user, UserGetDTO.class);
    }
}
