package com.edu.sena.Petcare.config;

import com.edu.sena.Petcare.models.*;
import com.edu.sena.Petcare.repository.*;
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
    private final DocumentTypeRepository documentTypeRepository;
    private final LocalityRepository localityRepository;
    private final NeighborhoodRepository neighborhoodRepository;

    @Override
    public void run(String... args) {

        // --- Roles ---
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

        // --- Document Types ---
        if (documentTypeRepository.count() == 0) {
            String[][] types = {
                {"Cédula de Ciudadanía", "CC"},
                {"Cédula de Extranjería", "CE"},
                {"Tarjeta de Identidad", "TI"},
                {"Pasaporte", "PP"},
                {"Registro Civil", "RC"},
                {"Número de Identificación Tributaria", "NIT"}
            };
            for (String[] t : types) {
                DocumentType dt = new DocumentType();
                dt.setName(t[0]);
                dt.setAbreviation(t[1]);
                documentTypeRepository.save(dt);
            }
        }

        // --- Localities & Neighborhoods ---
        if (localityRepository.count() == 0) {
            Locality locality = new Locality();
            locality.setName("Chapinero");
            locality = localityRepository.save(locality);

            String[] neighborhoods = {"El Retiro", "Antiguo Country", "Chicó Reservado"};
            for (String n : neighborhoods) {
                Neighborhood nb = new Neighborhood();
                nb.setName(n);
                nb.setLocality(locality);
                neighborhoodRepository.save(nb);
            }
        }

        // --- Admin ---
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
