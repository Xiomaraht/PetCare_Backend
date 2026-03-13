package com.edu.sena.Petcare.service.Impl;

import com.edu.sena.Petcare.dto.UserDTO;
import com.edu.sena.Petcare.models.Authority;
import com.edu.sena.Petcare.models.User;
import com.edu.sena.Petcare.repository.AuthorityRepository;
import com.edu.sena.Petcare.repository.UserRepository;
import com.edu.sena.Petcare.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDTO save(UserDTO userDTO) {
        if (userDTO.getUsername() == null || userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new RuntimeException("El nombre de usuario ya existe o es inválido");
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());

        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        user.setPassword(encodedPassword);

        String roleName = userDTO.getRole() != null ? userDTO.getRole() : "ROLE_USER";
        Authority authority = authorityRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + roleName));
        user.setAuthority(authority);

        // AbstractAuditingEntity handles createdAt/updatedAt via @PrePersist/Update

        User savedUser = userRepository.save(user);
        return mapToDTO(savedUser);
    }

    @Override
    public Optional<UserDTO> update(UserDTO userDTO) {
        return userRepository.findById(userDTO.getId())
                .map(existingUser -> {
                    if (userDTO.getUsername() != null)
                        existingUser.setUsername(userDTO.getUsername());
                    if (userDTO.getFirstName() != null)
                        existingUser.setFirstName(userDTO.getFirstName());
                    if (userDTO.getLastName() != null)
                        existingUser.setLastName(userDTO.getLastName());
                    if (userDTO.getEmail() != null)
                        existingUser.setEmail(userDTO.getEmail());
                    // Password update should be separate mostly, but basic support:
                    if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
                        existingUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
                    }
                    if (userDTO.getRole() != null) {
                        Authority authority = authorityRepository.findByName(userDTO.getRole())
                                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
                        existingUser.setAuthority(authority);
                    }
                    return mapToDTO(userRepository.save(existingUser));
                });
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> findOne(Long id) {
        return userRepository.findById(id).map(this::mapToDTO);
    }

    @Override
    public Optional<UserDTO> findByUsername(String username) {
        return userRepository.findByUsername(username).map(this::mapToDTO);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    private UserDTO mapToDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                null, // Password hidden
                user.getEmail(),
                user.getAuthority() != null ? user.getAuthority().getName() : null);
    }
}
