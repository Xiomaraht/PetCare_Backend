package com.edu.sena.Petcare.service.Impl;

import java.util.List;
import java.util.stream.Collectors; // IMPORTACIÓN CLAVE AÑADIDA

import org.springframework.stereotype.Service;

import com.edu.sena.Petcare.dto.PetDTO;
import com.edu.sena.Petcare.dto.PetRegistrationDTO;
import com.edu.sena.Petcare.mapper.PetMapper;
import com.edu.sena.Petcare.models.Customer;
import com.edu.sena.Petcare.models.Pet;
import com.edu.sena.Petcare.models.Race;
import com.edu.sena.Petcare.exception.ResourceNotFoundException;
import com.edu.sena.Petcare.repository.CustomerRepository;
import com.edu.sena.Petcare.repository.PetRepository;
import com.edu.sena.Petcare.repository.RaceRepository;
import com.edu.sena.Petcare.service.PetService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;
    private final RaceRepository raceRepository;
    private final CustomerRepository customerRepository;

    @Override
    public PetDTO register(PetRegistrationDTO dto) {
        Pet pet = PetMapper.toEntity(dto);

        Race race = raceRepository.findById(dto.getRaceId())
                .orElseThrow(() -> new ResourceNotFoundException("Race not found with ID: " + dto.getRaceId())); 

        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + dto.getCustomerId())); 

        pet.setRaza(race);
        pet.setCustomer(customer);

        return PetMapper.toDTO(petRepository.save(pet));
    }

    @Override
    public List<PetDTO> findAll() {
        return petRepository.findAll()
                .stream()
                .map(PetMapper::toDTO)
                .collect(Collectors.toList()); // CORREGIDO: Uso de collect
    }

    @Override
    public PetDTO findById(Long id) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet not found with ID: " + id)); 
        return PetMapper.toDTO(pet);
    }

    @Override
    public void delete(Long id) {
        if (!petRepository.existsById(id)) {
            throw new ResourceNotFoundException("Pet not found with ID: " + id + " for deletion");
        } 
        petRepository.deleteById(id);
    }

    @Override
    @Transactional
    public PetDTO update(Long id, PetRegistrationDTO dto) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet not found with ID: " + id + " for update")); 

        Race race = raceRepository.findById(dto.getRaceId())
                .orElseThrow(() -> new ResourceNotFoundException("Race not found with ID: " + dto.getRaceId())); 

        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + dto.getCustomerId()));
                
        pet.setRaza(race);
        pet.setCustomer(customer);

        // CORREGIDO: Usando setBirthdate (d minúscula)
        pet.setBirthdate(dto.getBirthdate()); 
        
        pet.setName(dto.getName());
        pet.setStatus(dto.getStatus());
        pet.setWeight(dto.getWeight());
        pet.setColor(dto.getColor());
        pet.setMicrochip(dto.getMicrochip());
        pet.setGender(dto.getGender()); 
        pet.setImageUrl(dto.getImageUrl()); 

        return PetMapper.toDTO(petRepository.save(pet));
    }

    @Override
    public List<PetDTO> findByCustomerId(Long customerId) {
        return petRepository.findByCustomerId(customerId)
                .stream()
                .map(PetMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PetDTO> findPetsByClinicId(Long clinicId) {
        return petRepository.findPetsByClinicId(clinicId).stream()
                .map(PetMapper::toDTO)
                .collect(Collectors.toList());
    }
}
