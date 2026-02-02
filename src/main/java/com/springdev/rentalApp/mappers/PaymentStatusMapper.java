package com.springdev.rentalApp.mappers;

import org.springframework.stereotype.Component;

import com.springdev.rentalApp.dtos.PaymentStatusDTO;
import com.springdev.rentalApp.entities.PaymentStatus;

@Component
public class PaymentStatusMapper {
    public PaymentStatusDTO toDto(PaymentStatus status) {
        return new PaymentStatusDTO(
            status.getId(),
            status.getLabel()
        );
    }

    public PaymentStatus toEntity(PaymentStatusDTO dto) {
        PaymentStatus status = new PaymentStatus();
        status.setId(dto.id());
        status.setLabel(dto.label());
        return status;
    }
}
