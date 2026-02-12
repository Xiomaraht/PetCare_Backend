package com.edu.sena.Petcare.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.edu.sena.Petcare.dto.UserCreationDTO;
import com.edu.sena.Petcare.models.User;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserCreationMapper {

    private final ModelMapper modelMapper;

    public User toEntity(UserCreationDTO userCreationDTO){
        return modelMapper.map(userCreationDTO, User.class);
    }

    public void toEntity(UserCreationDTO userCreationDTO, User userExistente){
        modelMapper.map(userCreationDTO, userExistente);
    }

    public UserCreationDTO toDTO(User user){
        return modelMapper.map(user, UserCreationDTO.class);
    }
}   
