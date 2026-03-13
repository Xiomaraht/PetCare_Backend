package com.edu.sena.Petcare.service;

import com.edu.sena.Petcare.dto.PaymentMethodDTO;

import java.util.List;

public interface PaymentMethodService {

    List<PaymentMethodDTO> findAll();

    PaymentMethodDTO findById(Long id);

    PaymentMethodDTO save(PaymentMethodDTO dto);

    PaymentMethodDTO update(Long id, PaymentMethodDTO dto);

    void delete(Long id);

}
