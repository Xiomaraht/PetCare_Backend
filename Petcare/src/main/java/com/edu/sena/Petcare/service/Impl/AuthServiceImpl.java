package com.edu.sena.Petcare.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.edu.sena.Petcare.Controller.Request.RegisterRequest;
import com.edu.sena.Petcare.models.Authority;
import com.edu.sena.Petcare.models.User;
import com.edu.sena.Petcare.repository.AuthorityRepository;
import com.edu.sena.Petcare.repository.UserRepository;
import com.edu.sena.Petcare.service.AuthService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
public void register(RegisterRequest request) {

    System.out.println("USERNAME RECIBIDO: " + request.getUsername());

    Authority roleUser = authorityRepository.findByName("ROLE_USER")
            .orElseThrow(() -> new RuntimeException("Rol ROLE_USER no existe"));

    User user = User.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .authority(roleUser)
            .build();

    userRepository.save(user);
}
}