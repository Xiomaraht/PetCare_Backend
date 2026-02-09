package com.edu.sena.Petcare.service;

import com.edu.sena.Petcare.models.User;

import java.util.List;

public interface UserService {

    User save(User user);

    List<User> findAll();

    User findById(Long id);
}

