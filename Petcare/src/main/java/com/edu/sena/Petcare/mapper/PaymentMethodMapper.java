package com.edu.sena.Petcare.mapper;

import com.edu.sena.Petcare.dto.PaymentMethodDTO;
import com.edu.sena.Petcare.models.Customer;
import com.edu.sena.Petcare.models.PaymentMethod;

public class PaymentMethodMapper {

    public static PaymentMethodDTO toDTO(PaymentMethod entity) {
        PaymentMethodDTO dto = new PaymentMethodDTO();

        dto.setId(entity.getId());
        dto.setTokenPaymentMethod(entity.getTokenPaymentMethod());
        dto.setTokenClientGateway(entity.getTokenClientGateway());
        dto.setBrand(entity.getBrand());
        dto.setLastFourDigits(entity.getLastFourDigits());
        dto.setExpirationDate(entity.getExpirationDate());
        dto.setIsDefault(entity.getIsDefault());

        if(entity.getCustomer() != null){
            dto.setCustomerId(entity.getCustomer().getId());
        }

        return dto;
    }

    public static PaymentMethod toEntity(PaymentMethodDTO dto) {

        PaymentMethod entity = new PaymentMethod();

        entity.setId(dto.getId());
        entity.setTokenPaymentMethod(dto.getTokenPaymentMethod());
        entity.setTokenClientGateway(dto.getTokenClientGateway());
        entity.setBrand(dto.getBrand());
        entity.setLastFourDigits(dto.getLastFourDigits());
        entity.setExpirationDate(dto.getExpirationDate());
        entity.setIsDefault(dto.getIsDefault());

        if(dto.getCustomerId() != null){
            Customer customer = new Customer();
            customer.setId(dto.getCustomerId());
            entity.setCustomer(customer);
        }

        return entity;
    }
}
