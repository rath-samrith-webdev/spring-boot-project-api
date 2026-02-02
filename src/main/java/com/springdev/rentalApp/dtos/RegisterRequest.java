package com.springdev.rentalApp.dtos;

public record RegisterRequest(
    String firstName,
    String lastName,
    String email,
    String password
) {}
