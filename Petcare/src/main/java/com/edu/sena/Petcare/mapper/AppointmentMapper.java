package com.edu.sena.Petcare.mapper;

import com.edu.sena.Petcare.dto.AppointmentDTO;
import com.edu.sena.Petcare.models.Appointment;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {

    public AppointmentDTO toDTO(Appointment appointment) {
        if (appointment == null) {
            return null;
        }

        return AppointmentDTO.builder()
                .id(appointment.getId())
                .appointmentDate(appointment.getAppointmentDate())
                .appointmentTime(appointment.getAppointmentTime())
                .reason(appointment.getReason())
                .status(appointment.getStatus() != null ? appointment.getStatus().name() : null)
                .customerId(appointment.getCustomer() != null ? appointment.getCustomer().getId() : null)
                .customerName(appointment.getCustomer() != null ? appointment.getCustomer().getName() : null)
                .veterinaryClinicId(appointment.getVeterinaryClinic() != null ? appointment.getVeterinaryClinic().getId() : null)
                .veterinaryClinicName(appointment.getVeterinaryClinic() != null ? appointment.getVeterinaryClinic().getName() : null)
                .petId(appointment.getPet() != null ? appointment.getPet().getId() : null)
                .petName(appointment.getPet() != null ? appointment.getPet().getName() : null)
                .serviceId(appointment.getService() != null ? appointment.getService().getId() : null)
                .serviceName(appointment.getService() != null ? appointment.getService().getName() : null)
                .build();
    }
}
