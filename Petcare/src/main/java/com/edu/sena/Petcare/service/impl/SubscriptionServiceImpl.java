package com.edu.sena.Petcare.service.impl;

import com.edu.sena.Petcare.dto.SubscriptionPlanDTO;
import com.edu.sena.Petcare.dto.ClinicSubscriptionDTO;
import com.edu.sena.Petcare.models.*;
import com.edu.sena.Petcare.repository.*;
import com.edu.sena.Petcare.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionPlanRepository planRepository;
    private final ClinicSubscriptionRepository clinicSubRepository;
    private final VeterinaryClinicRepository clinicRepository;

    @Override
    public SubscriptionPlanDTO createPlan(SubscriptionPlanDTO planDTO) {
        SubscriptionPlan plan = SubscriptionPlan.builder()
                .name(planDTO.getName())
                .price(planDTO.getPrice())
                .durationDays(planDTO.getDurationDays())
                .description(planDTO.getDescription())
                .active(true)
                .build();
        return mapToPlanDTO(planRepository.save(plan));
    }

    @Override
    public List<SubscriptionPlanDTO> getAllPlans() {
        return planRepository.findAll().stream()
                .map(this::mapToPlanDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public SubscriptionPlanDTO updatePlan(Long id, SubscriptionPlanDTO planDTO) {
        SubscriptionPlan plan = planRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plan no encontrado"));
        plan.setName(planDTO.getName());
        plan.setPrice(planDTO.getPrice());
        plan.setDurationDays(planDTO.getDurationDays());
        plan.setDescription(planDTO.getDescription());
        plan.setActive(planDTO.getActive());
        return mapToPlanDTO(planRepository.save(plan));
    }

    @Override
    public void deletePlan(Long id) {
        planRepository.deleteById(id);
    }

    @Override
    @Transactional
    public ClinicSubscriptionDTO subscribeClinic(Long clinicId, Long planId) {
        VeterinaryClinic clinic = clinicRepository.findById(clinicId)
                .orElseThrow(() -> new RuntimeException("Clínica no encontrada"));
        SubscriptionPlan plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan no encontrado"));

        LocalDateTime now = LocalDateTime.now();
        ClinicSubscription sub = clinicSubRepository.findByClinic_Id(clinicId)
                .orElse(new ClinicSubscription());

        sub.setClinic(clinic);
        sub.setPlan(plan);
        sub.setStartDate(now);
        sub.setEndDate(now.plusDays(plan.getDurationDays()));
        sub.setStatus(SubscriptionStatus.ACTIVE);
        
        return mapToClinicSubDTO(clinicSubRepository.save(sub));
    }

    @Override
    public ClinicSubscriptionDTO getClinicSubscription(Long clinicId) {
        return clinicSubRepository.findByClinic_Id(clinicId)
                .map(this::mapToClinicSubDTO)
                .orElseThrow(() -> new RuntimeException("Suscripción no encontrada para esta clínica"));
    }

    @Override
    public List<ClinicSubscriptionDTO> getAllClinicSubscriptions() {
        return clinicSubRepository.findAll().stream()
                .map(this::mapToClinicSubDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ClinicSubscriptionDTO updateSubscriptionStatus(Long id, String status) {
        ClinicSubscription sub = clinicSubRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Suscripción no encontrada"));
        sub.setStatus(SubscriptionStatus.valueOf(status.toUpperCase()));
        return mapToClinicSubDTO(clinicSubRepository.save(sub));
    }

    @Override
    @Transactional
    public void cancelSubscription(Long id) {
        ClinicSubscription sub = clinicSubRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Suscripción no encontrada"));
        sub.setStatus(SubscriptionStatus.CANCELLED);
        clinicSubRepository.save(sub);
    }

    private SubscriptionPlanDTO mapToPlanDTO(SubscriptionPlan plan) {
        return SubscriptionPlanDTO.builder()
                .id(plan.getId())
                .name(plan.getName())
                .price(plan.getPrice())
                .durationDays(plan.getDurationDays())
                .description(plan.getDescription())
                .active(plan.getActive())
                .build();
    }

    private ClinicSubscriptionDTO mapToClinicSubDTO(ClinicSubscription sub) {
        return ClinicSubscriptionDTO.builder()
                .id(sub.getId())
                .clinicId(sub.getClinic().getId())
                .clinicName(sub.getClinic().getUser() != null ? sub.getClinic().getUser().getUsername() : "Clínica " + sub.getClinic().getId())
                .planId(sub.getPlan().getId())
                .planName(sub.getPlan().getName())
                .startDate(sub.getStartDate())
                .endDate(sub.getEndDate())
                .status(sub.getStatus())
                .paymentReference(sub.getPaymentReference())
                .autoRenew(sub.getAutoRenew())
                .build();
    }
}
