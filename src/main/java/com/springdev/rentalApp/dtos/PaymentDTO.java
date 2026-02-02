package com.springdev.rentalApp.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentDTO(
    Long id,
    Long utilityId,
    Long paidById,
    Long paymentStatusId,
    String referenceNumber,
    BigDecimal amount,
    String paymentMethod,
    String currency,
    String note,
    Long verifiedById,
    LocalDateTime paidOn,
    LocalDateTime verifiedOn,
    LocalDateTime createdAt
) {}
