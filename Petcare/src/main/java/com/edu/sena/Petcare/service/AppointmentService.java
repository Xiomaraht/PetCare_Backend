package com.edu.sena.Petcare.service;


import com.edu.sena.Petcare.dto.AppointmentDTO;
import java.util.List;

public interface AppointmentService {
    AppointmentDTO create(AppointmentDTO appointmentDto);
    AppointmentDTO createFromDto(com.edu.sena.Petcare.dto.AppointmentRegistrationDTO dto);
    List<AppointmentDTO> findAll();
    List<AppointmentDTO> findByCustomerId(Long customerId);
    List<AppointmentDTO> findByClinicId(Long clinicId);
    List<AppointmentDTO> findByPetId(Long petId);
    AppointmentDTO updateStatus(Long id, String status);
    void delete(Long id);
}
