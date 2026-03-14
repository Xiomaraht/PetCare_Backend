package com.edu.sena.Petcare.repository;

import com.edu.sena.Petcare.models.ClinicSubscription;
import com.edu.sena.Petcare.models.SubscriptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface ClinicSubscriptionRepository extends JpaRepository<ClinicSubscription, Long> {
    Optional<ClinicSubscription> findByClinic_Id(Long clinicId);
    List<ClinicSubscription> findByStatus(SubscriptionStatus status);
}
