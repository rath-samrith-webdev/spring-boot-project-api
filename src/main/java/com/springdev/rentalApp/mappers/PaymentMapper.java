package com.springdev.rentalApp.mappers;

import org.springframework.stereotype.Component;

import com.springdev.rentalApp.dtos.PaymentDTO;
import com.springdev.rentalApp.entities.Payment;
import com.springdev.rentalApp.entities.PaymentStatus;
import com.springdev.rentalApp.entities.User;
import com.springdev.rentalApp.entities.Utility;

@Component
public class PaymentMapper {
    public PaymentDTO toDto(Payment payment) {
        return new PaymentDTO(
            payment.getId(),
            payment.getUtility().getId(),
            payment.getPaidBy().getId(),
            payment.getPaymentStatus() != null ? payment.getPaymentStatus().getId() : null,
            payment.getReferenceNumber(),
            payment.getAmount(),
            payment.getPaymentMethod(),
            payment.getCurrency(),
            payment.getNote(),
            payment.getVerifiedBy() != null ? payment.getVerifiedBy().getId() : null,
            payment.getPaidOn(),
            payment.getVerifiedOn(),
            payment.getCreatedAt()
        );
    }

    public Payment toEntity(PaymentDTO dto, Utility utility, User paidBy, PaymentStatus paymentStatus, User verifiedBy) {
        Payment payment = new Payment();
        payment.setId(dto.id());
        payment.setUtility(utility);
        payment.setPaidBy(paidBy);
        payment.setPaymentStatus(paymentStatus);
        payment.setReferenceNumber(dto.referenceNumber());
        payment.setAmount(dto.amount());
        payment.setPaymentMethod(dto.paymentMethod());
        payment.setCurrency(dto.currency());
        payment.setNote(dto.note());
        payment.setVerifiedBy(verifiedBy);
        payment.setPaidOn(dto.paidOn());
        payment.setVerifiedOn(dto.verifiedOn());
        return payment;
    }
}
