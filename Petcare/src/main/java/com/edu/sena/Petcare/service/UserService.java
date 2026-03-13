package com.edu.sena.Petcare.service;

import com.edu.sena.Petcare.dto.UserDTO;
import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDTO save(UserDTO userDTO);

    Optional<UserDTO> update(UserDTO userDTO);

    List<UserDTO> findAll();

    Optional<UserDTO> findOne(Long id);

    Optional<UserDTO> findByUsername(String username);

    void delete(Long id);
}
