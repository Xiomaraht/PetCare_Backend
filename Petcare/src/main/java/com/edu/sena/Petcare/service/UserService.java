package com.edu.sena.Petcare.service;

import java.util.List;

import com.edu.sena.Petcare.dto.UserCreationDTO;
import com.edu.sena.Petcare.dto.UserDTO;
import com.edu.sena.Petcare.dto.UserUpdateDTO;

public interface UserService {

    List<User> findAll();
    User findById(Long id);
    User save(User user);
    void eliminarUser(Long id);
}
