package com.springdev.rentalApp.dtos;

public record UserMonthlySavingDTO(
    Long id,
    Long userId,
    Integer year,
    Integer month,
    Double amount,
    String status,
    String createdAt,
    String updatedAt
) {}
