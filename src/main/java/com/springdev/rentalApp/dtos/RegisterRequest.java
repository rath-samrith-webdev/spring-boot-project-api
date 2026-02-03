package com.springdev.rentalApp.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

public record RegisterRequest(
    @Schema(example = "John")
    String firstName,
    @Schema(example = "Doe")
    String lastName,
    @Schema(example = "john.doe@example.com")
    String email,
    @Schema(example = "123 Main St, Anytown, USA")
    String currentAddress,
    @Schema(example = "555-1234")
    String phoneNumber,
    @Schema(example = "password123")
    String password
) {}
