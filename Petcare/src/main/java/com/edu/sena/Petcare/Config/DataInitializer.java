package com.edu.sena.Petcare.config;

import com.edu.sena.Petcare.models.Authority;
import com.edu.sena.Petcare.models.User;
import com.edu.sena.Petcare.repository.AuthorityRepository;
import com.edu.sena.Petcare.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        Authority adminRole = authorityRepository.findByName("ROLE_ADMIN")
                .orElseGet(() -> authorityRepository.save(
                        Authority.builder().name("ROLE_ADMIN").build()
                ));

        authorityRepository.findByName("ROLE_USER")
                .orElseGet(() -> authorityRepository.save(
                        Authority.builder().name("ROLE_USER").build()
                ));

        authorityRepository.findByName("ROLE_CUSTOMER")
                .orElseGet(() -> authorityRepository.save(
                        Authority.builder().name("ROLE_CUSTOMER").build()
                ));

        authorityRepository.findByName("ROLE_VETERINARIAN")
                .orElseGet(() -> authorityRepository.save(
                        Authority.builder().name("ROLE_VETERINARIAN").build()
                ));

        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin123"))
                    .authority(adminRole)
                    .build();

            userRepository.save(admin);

            System.out.println("ADMIN creado: admin / admin123");
        }
    }
}
