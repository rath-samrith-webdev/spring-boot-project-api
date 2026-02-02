package com.springdev.rentalApp.dtos;

public record UserUtilityDTO(
    Long userId,
    Long utilityId,
    String role
) {}
