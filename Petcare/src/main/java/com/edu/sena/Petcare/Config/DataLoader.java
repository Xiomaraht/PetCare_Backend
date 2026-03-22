package com.edu.sena.Petcare.config;

import com.edu.sena.Petcare.models.Race;
import com.edu.sena.Petcare.models.Specie;
import com.edu.sena.Petcare.repository.RaceRepository;
import com.edu.sena.Petcare.repository.SpecieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader {

    private final SpecieRepository specieRepository;
    private final RaceRepository raceRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void loadData() {
        if (specieRepository.count() == 0) {
            Specie dog = new Specie();
            dog.setName("Perro");
            Specie cat = new Specie();
            cat.setName("Gato");
            specieRepository.saveAll(Arrays.asList(dog, cat));
        }

        Specie dog = specieRepository.findAll().stream()
                .filter(s -> s.getName().equalsIgnoreCase("Perro") || s.getName().equalsIgnoreCase("Dog"))
                .findFirst().orElse(null);
        
        Specie cat = specieRepository.findAll().stream()
                .filter(s -> s.getName().equalsIgnoreCase("Gato") || s.getName().equalsIgnoreCase("Cat"))
                .findFirst().orElse(null);

        if (dog != null && cat != null) {
            List<String> dogBreeds = Arrays.asList(
                "Labrador Retriever", "Pastor Alemán", "Golden Retriever", "Bulldog Francés",
                "Bulldog Inglés", "Beagle", "Poodle", "Rottweiler", "Yorkshire Terrier",
                "Boxer", "Dachshund", "Siberian Husky", "Shih Tzu", "Doberman", "Pug",
                "Pomeranian", "Boston Terrier", "Chihuahua", "Border Collie", "Mestizo (Perro)",
                "Pincher", "Pomeranie"
            );

            List<String> catBreeds = Arrays.asList(
                "Persa", "Maine Coon", "Siamés", "Ragdoll", "Sphynx", "Abisinio",
                "Azul Ruso", "Bengala", "British Shorthair", "Scottish Fold",
                "Burmés", "Siberiano", "Himalayo", "Munchkin", "Angora Turco",
                "Devon Rex", "Bobtail Japonés", "Mestizo (Gato)"
            );

            for (String name : dogBreeds) {
                boolean exists = raceRepository.findAll().stream().anyMatch(r -> r.getName().equalsIgnoreCase(name));
                if (!exists) {
                    Race race = new Race();
                    race.setName(name);
                    race.setEspecie(dog);
                    raceRepository.save(race);
                }
            }

            for (String name : catBreeds) {
                boolean exists = raceRepository.findAll().stream().anyMatch(r -> r.getName().equalsIgnoreCase(name));
                if (!exists) {
                    Race race = new Race();
                    race.setName(name);
                    race.setEspecie(cat);
                    raceRepository.save(race);
                }
            }
        }
    }
}
