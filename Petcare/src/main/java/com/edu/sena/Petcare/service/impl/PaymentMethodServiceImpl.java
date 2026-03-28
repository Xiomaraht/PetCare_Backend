package com.edu.sena.Petcare.service.impl;

import com.edu.sena.Petcare.dto.PaymentMethodDTO;
import com.edu.sena.Petcare.mapper.PaymentMethodMapper;
import com.edu.sena.Petcare.models.PaymentMethod;
import com.edu.sena.Petcare.repository.PaymentMethodRepository;
import com.edu.sena.Petcare.service.PaymentMethodService;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {

    private final PaymentMethodRepository repository;

    public PaymentMethodServiceImpl(PaymentMethodRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PaymentMethodDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(PaymentMethodMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentMethodDTO findById(Long id) {
        PaymentMethod entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Metodo de pago no encontrado"));

        return PaymentMethodMapper.toDTO(entity);
    }

    @Override
    public PaymentMethodDTO save(PaymentMethodDTO dto) {

        PaymentMethod entity = PaymentMethodMapper.toEntity(dto);
        entity.setCreationDate(LocalDateTime.now());

        return PaymentMethodMapper.toDTO(repository.save(entity));
    }

    @Override
    public PaymentMethodDTO update(Long id, PaymentMethodDTO dto) {

        PaymentMethod entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Metodo no encontrado"));

        entity.setBrand(dto.getBrand());
        entity.setLastFourDigits(dto.getLastFourDigits());
        entity.setExpirationDate(dto.getExpirationDate());
        entity.setIsDefault(dto.getIsDefault());

        return PaymentMethodMapper.toDTO(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
