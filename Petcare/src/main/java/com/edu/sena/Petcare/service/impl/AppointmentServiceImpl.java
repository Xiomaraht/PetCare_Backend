package com.edu.sena.Petcare.service.impl;

import com.edu.sena.Petcare.dto.AppointmentDTO;
import com.edu.sena.Petcare.mapper.AppointmentMapper;
import com.edu.sena.Petcare.models.Appointment;
import com.edu.sena.Petcare.models.AppointmentStatus;
import com.edu.sena.Petcare.dto.AppointmentRegistrationDTO;
import com.edu.sena.Petcare.repository.*;
import com.edu.sena.Petcare.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final CustomerRepository customerRepository;
    private final VeterinaryClinicRepository veterinaryClinicRepository;
    private final ServicesRepository serviceRepository;
    private final PetRepository petRepository;
    private final AppointmentMapper appointmentMapper;

    @Override
    public AppointmentDTO create(AppointmentDTO appointmentDto) {
        // This method is rarely used directly if createFromDto exists, 
        // but kept for interface consistency.
        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(appointmentDto.getAppointmentDate());
        appointment.setAppointmentTime(appointmentDto.getAppointmentTime());
        appointment.setReason(appointmentDto.getReason());
        
        try {
            appointment.setStatus(appointmentDto.getStatus() != null ? 
                AppointmentStatus.valueOf(appointmentDto.getStatus().toUpperCase()) : 
                AppointmentStatus.PENDING);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Estado de cita inválido: " + appointmentDto.getStatus());
        }
        
        return appointmentMapper.toDTO(appointmentRepository.save(appointment));
    }

    @Override
    public AppointmentDTO createFromDto(AppointmentRegistrationDTO dto) {
        Appointment appointment = new Appointment();
        
        if (dto.getAppointmentDate() != null) {
            appointment.setAppointmentDate(LocalDate.parse(dto.getAppointmentDate()));
        }
        
        if (dto.getAppointmentTime() != null) {
            String timeStr = dto.getAppointmentTime();
            if (timeStr.length() == 5) timeStr += ":00";
            appointment.setAppointmentTime(LocalTime.parse(timeStr));
        }
        
        appointment.setReason(dto.getReason());

        try {
            appointment.setStatus(dto.getStatus() != null ? 
                AppointmentStatus.valueOf(dto.getStatus().toUpperCase()) : 
                AppointmentStatus.PENDING);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Estado de cita inválido: " + dto.getStatus());
        }

        appointment.setCustomer(customerRepository.findById(dto.getCustomerId())
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + dto.getCustomerId())));
        
        appointment.setVeterinaryClinic(veterinaryClinicRepository.findById(dto.getClinicId())
            .orElseThrow(() -> new RuntimeException("Clínica no encontrada con id: " + dto.getClinicId())));

        if (dto.getServiceId() != null) {
            appointment.setService(serviceRepository.findById(dto.getServiceId())
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado con id: " + dto.getServiceId())));
        }

        if (dto.getPetId() != null) {
            appointment.setPet(petRepository.findById(dto.getPetId())
                .orElseThrow(() -> new RuntimeException("Pet no encontrado con id: " + dto.getPetId())));
        }

        return appointmentMapper.toDTO(appointmentRepository.save(appointment));
    }

    @Override
    public List<AppointmentDTO> findAll() {
        return appointmentRepository.findAll().stream()
                .map(appointmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> findByCustomerId(Long customerId) {
        return appointmentRepository.findByCustomerId(customerId).stream()
                .map(appointmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> findByClinicId(Long clinicId) {
        return appointmentRepository.findByVeterinaryClinicId(clinicId).stream()
                .map(appointmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentDTO updateStatus(Long id, String status) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
        appointment.setStatus(AppointmentStatus.valueOf(status.toUpperCase()));
        return appointmentMapper.toDTO(appointmentRepository.save(appointment));
    }

    @Override
    public List<AppointmentDTO> findByPetId(Long petId) {
        return appointmentRepository.findByPetId(petId).stream()
                .map(appointmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        appointmentRepository.deleteById(id);
    }
}
