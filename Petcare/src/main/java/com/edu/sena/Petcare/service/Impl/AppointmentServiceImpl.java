package com.edu.sena.Petcare.service.Impl;

import com.edu.sena.Petcare.models.Appointment;
import com.edu.sena.Petcare.models.AppointmentStatus;
import com.edu.sena.Petcare.repository.AppointmentRepository;
import com.edu.sena.Petcare.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Override
    public Appointment create(Appointment appointment) {
        if (appointment.getStatus() == null) {
            appointment.setStatus(AppointmentStatus.PENDING);
        }
        return appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    @Override
    public List<Appointment> findByCustomerId(Long customerId) {
        return appointmentRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Appointment> findByClinicId(Long clinicId) {
        return appointmentRepository.findByVeterinaryClinicId(clinicId);
    }

    @Override
    public Appointment updateStatus(Long id, String status) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
        appointment.setStatus(AppointmentStatus.valueOf(status.toUpperCase()));
        return appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> findByPetId(Long petId) {
        return appointmentRepository.findByPetId(petId);
    }

    @Override
    public void delete(Long id) {
        appointmentRepository.deleteById(id);
    }
}
