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
    private final SpecieRepository specieRepository;
    private final RaceRepository raceRepository;
    private final SubscriptionPlanRepository subscriptionPlanRepository;

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

        // --- Species & Races ---
        if (specieRepository.count() == 0) {
            Specie perro = new Specie();
            perro.setName("Perro");
            perro = specieRepository.save(perro);

            Specie gato = new Specie();
            gato.setName("Gato");
            gato = specieRepository.save(gato);

            // Razas Perro
            String[] razasPerro = {
                "Labrador", "Golden Retriever", "Poodle", "Bulldog", "Pastor Alemán", 
                "Yorkshire Terrier", "Chihuahua", "Beagle", "Boxer", "Husky Siberiano",
                "Shih Tzu", "Pinscher", "Pug", "Rottweiler", "Border Collie", 
                "Greyhound", "Dálmata", "Cocker Spaniel", "Doberman", "San Bernardo",
                "Schnauzer", "Bichón Maltés", "Gran Danés", "Chow Chow", "Akita"
            };
            for (String r : razasPerro) {
                Race race = new Race();
                race.setName(r);
                race.setEspecie(perro);
                raceRepository.save(race);
            }

            // Razas Gato
            String[] razasGato = {"Persa", "Maine Coon", "Siamés", "Bengalí", "Sphynx", "Ragdoll", "Abisinio", "Birmano", "Ruso Azul", "Angora"};
            for (String r : razasGato) {
                Race race = new Race();
                race.setName(r);
                race.setEspecie(gato);
                raceRepository.save(race);
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

        // --- Subscription Plans ---
        if (subscriptionPlanRepository.count() == 0) {
            SubscriptionPlan basic = SubscriptionPlan.builder()
                .name("Plan Básico")
                .price(29.99)
                .durationDays(30)
                .description("Acceso esencial para gestión de citas y productos.")
                .active(true)
                .build();
            
            SubscriptionPlan pro = SubscriptionPlan.builder()
                .name("Plan Profesional")
                .price(59.99)
                .durationDays(30)
                .description("Gestión completa con analíticas y reportes avanzados.")
                .active(true)
                .build();
            
            SubscriptionPlan premium = SubscriptionPlan.builder()
                .name("Plan Premium")
                .price(499.99)
                .durationDays(365)
                .description("Suscripción anual con todos los beneficios y soporte prioritario.")
                .active(true)
                .build();

            subscriptionPlanRepository.save(basic);
            subscriptionPlanRepository.save(pro);
            subscriptionPlanRepository.save(premium);
            System.out.println("Planes de suscripción inicializados.");
        }
    }
}
