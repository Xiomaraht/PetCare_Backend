package com.edu.sena.Petcare.service;

import com.edu.sena.Petcare.dto.BillDTO;
import java.util.List;

public interface BillService {
    List<BillDTO> findByVeterinaryClinicId(Long clinicId);
}
