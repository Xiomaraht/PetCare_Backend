package com.edu.sena.Petcare.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.edu.sena.Petcare.dto.UserDTO;
import com.edu.sena.Petcare.models.User;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper modelMapper;

    public User toEntity(UserDTO userDTO){
        return modelMapper.map(userDTO, User.class);
    }

    public void toEntity(UserDTO userDTO, User userExistente){
        modelMapper.map(userDTO, userExistente);
    }

    public UserDTO toDTO(User user){
        return modelMapper.map(user, UserDTO.class);
    }
}
