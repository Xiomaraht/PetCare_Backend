package com.edu.sena.Petcare.service;

import com.edu.sena.Petcare.dto.SubscriptionPlanDTO;
import com.edu.sena.Petcare.dto.ClinicSubscriptionDTO;
import java.util.List;

public interface SubscriptionService {
    // Plan Management (Super Admin)
    SubscriptionPlanDTO createPlan(SubscriptionPlanDTO planDTO);
    List<SubscriptionPlanDTO> getAllPlans();
    SubscriptionPlanDTO updatePlan(Long id, SubscriptionPlanDTO planDTO);
    void deletePlan(Long id);

    // Clinic Subscriptions
    ClinicSubscriptionDTO subscribeClinic(Long clinicId, Long planId);
    ClinicSubscriptionDTO getClinicSubscription(Long clinicId);
    List<ClinicSubscriptionDTO> getAllClinicSubscriptions();
    ClinicSubscriptionDTO updateSubscriptionStatus(Long id, String status);
    void cancelSubscription(Long id);
}
