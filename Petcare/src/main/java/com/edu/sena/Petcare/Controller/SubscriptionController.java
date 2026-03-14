package com.edu.sena.Petcare.controller;

import com.edu.sena.Petcare.dto.SubscriptionPlanDTO;
import com.edu.sena.Petcare.dto.ClinicSubscriptionDTO;
import com.edu.sena.Petcare.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    // Plans
    @PostMapping("/plans")
    public ResponseEntity<SubscriptionPlanDTO> createPlan(@RequestBody SubscriptionPlanDTO planDTO) {
        return ResponseEntity.ok(subscriptionService.createPlan(planDTO));
    }

    @GetMapping("/plans")
    public ResponseEntity<List<SubscriptionPlanDTO>> getAllPlans() {
        return ResponseEntity.ok(subscriptionService.getAllPlans());
    }

    @PutMapping("/plans/{id}")
    public ResponseEntity<SubscriptionPlanDTO> updatePlan(@PathVariable Long id, @RequestBody SubscriptionPlanDTO planDTO) {
        return ResponseEntity.ok(subscriptionService.updatePlan(id, planDTO));
    }

    @DeleteMapping("/plans/{id}")
    public ResponseEntity<Void> deletePlan(@PathVariable Long id) {
        subscriptionService.deletePlan(id);
        return ResponseEntity.noContent().build();
    }

    // Clinic Subscriptions
    @PostMapping("/subscribe/{clinicId}/{planId}")
    public ResponseEntity<ClinicSubscriptionDTO> subscribeClinic(@PathVariable Long clinicId, @PathVariable Long planId) {
        return ResponseEntity.ok(subscriptionService.subscribeClinic(clinicId, planId));
    }

    @GetMapping("/clinic/{clinicId}")
    public ResponseEntity<ClinicSubscriptionDTO> getClinicSubscription(@PathVariable Long clinicId) {
        return ResponseEntity.ok(subscriptionService.getClinicSubscription(clinicId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ClinicSubscriptionDTO>> getAllClinicSubscriptions() {
        return ResponseEntity.ok(subscriptionService.getAllClinicSubscriptions());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ClinicSubscriptionDTO> updateSubscriptionStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(subscriptionService.updateSubscriptionStatus(id, status));
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelSubscription(@PathVariable Long id) {
        subscriptionService.cancelSubscription(id);
        return ResponseEntity.ok().build();
    }
}
