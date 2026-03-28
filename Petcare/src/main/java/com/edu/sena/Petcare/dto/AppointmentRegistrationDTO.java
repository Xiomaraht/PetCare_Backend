package com.edu.sena.Petcare.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentRegistrationDTO {
    
    @JsonAlias({"date", "fecha"})
    private String appointmentDate;

    @JsonAlias({"time", "hora"})
    private String appointmentTime;

    private String reason;

    @JsonProperty("customerId")
    @JsonAlias({"id_cliente", "clienteId", "id_customer"})
    private Long customerId;

    @JsonProperty("clinicId")
    @JsonAlias({"id_veterinaria", "clinicaId", "id_clinic"})
    private Long clinicId;

    @JsonProperty("serviceId")
    @JsonAlias({"id_servicio", "servicioId", "id_service"})
    private Long serviceId;

    @JsonProperty("petId")
    @JsonAlias({"id_mascota", "mascotaId", "id_pet"})
    private Long petId;

    private String status;
}
