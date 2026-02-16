package com.edu.sena.Petcare.service;

import com.edu.sena.Petcare.models.User;
import java.util.List;

public interface UserService {

    List<User> findAll();
    User findById(Long id);
    User save(User user);
    void eliminarUser(Long id);
}
