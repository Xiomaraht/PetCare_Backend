package com.edu.sena.Petcare.service.Impl;

import java.util.List;
import java.util.ArrayList;

import com.edu.sena.Petcare.dto.ServicesDTO;
import com.edu.sena.Petcare.dto.ServicesRegistrationDTO;
import com.edu.sena.Petcare.mapper.ServiceMapper;
import com.edu.sena.Petcare.models.Services; 
import com.edu.sena.Petcare.models.VeterinaryClinic;
import com.edu.sena.Petcare.repository.ServicesRepository;
import com.edu.sena.Petcare.repository.VeterinaryClinicRepository;
import com.edu.sena.Petcare.service.ServicesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@org.springframework.transaction.annotation.Transactional
public class ServicesServiceImpl implements ServicesService {

    private final ServicesRepository serviceRepository;
    private final VeterinaryClinicRepository veterinaryClinicRepository;

    @Override
    public ServicesDTO create(ServicesRegistrationDTO dto) {
        
        Services service = ServiceMapper.toEntity(dto);

        if (dto.getVeterinaryClinicIds() != null && !dto.getVeterinaryClinicIds().isEmpty()) {
            List<VeterinaryClinic> clinics = veterinaryClinicRepository.findAllById(dto.getVeterinaryClinicIds());
            service.setVeterinaryClinics(clinics);
        } else {
            service.setVeterinaryClinics(new ArrayList<>());
        }
         
        return ServiceMapper.toDTO(serviceRepository.save(service));
    }

    @Override
    public ServicesDTO update(Long id, ServicesRegistrationDTO dto) {

        Services service = serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));

        service.setStatus(dto.getStatus());
        service.setName(dto.getName());
        service.setDescription(dto.getDescription());
        service.setPicture(dto.getPicture());

        if (dto.getVeterinaryClinicIds() != null && !dto.getVeterinaryClinicIds().isEmpty()) {
            service.setVeterinaryClinics(
                    veterinaryClinicRepository.findAllById(dto.getVeterinaryClinicIds())
            );
        } else {
            service.setVeterinaryClinics(new ArrayList<>());
        }

        return ServiceMapper.toDTO(serviceRepository.save(service));
    }

    @Override
    public List<ServicesDTO> findAll() {
        return serviceRepository.findAll()
                .stream()
                .map(ServiceMapper::toDTO)
                .toList();
    }

    @Override
    public ServicesDTO findById(Long id) {
        Services service = serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));
        return ServiceMapper.toDTO(service);
    }

    @Override
    public void delete(Long id) {
        if (!serviceRepository.existsById(id)) {
            throw new RuntimeException("Service not found");
        }
        serviceRepository.deleteById(id);
    }
}
