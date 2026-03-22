package com.edu.sena.Petcare.service;

import com.edu.sena.Petcare.models.Appointment;
import java.util.List;

public interface AppointmentService {
    Appointment create(Appointment appointment);
    List<Appointment> findAll();
    List<Appointment> findByCustomerId(Long customerId);
    List<Appointment> findByClinicId(Long clinicId);
    List<Appointment> findByPetId(Long petId);
    Appointment updateStatus(Long id, String status);
    void delete(Long id);
}
