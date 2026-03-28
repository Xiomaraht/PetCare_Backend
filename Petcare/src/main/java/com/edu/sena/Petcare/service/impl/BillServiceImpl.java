package com.edu.sena.Petcare.service.impl;

import com.edu.sena.Petcare.dto.BillDTO;
import com.edu.sena.Petcare.models.Bill;
import com.edu.sena.Petcare.repository.BillRepository;
import com.edu.sena.Petcare.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;

    @Override
    public List<BillDTO> findByVeterinaryClinicId(Long clinicId) {
        return billRepository.findByVeterinaryClinicId(clinicId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private BillDTO mapToDTO(Bill bill) {
        return BillDTO.builder()
                .id(bill.getId())
                .date(bill.getBillDate())
                .total(bill.getTotal())
                .customerId(bill.getCustomer().getId())
                .customerName(bill.getCustomer().getName())
                .paymentStatus("PAID") // Hardcoded for now, model doesn't have it
                .build();
    }
}
