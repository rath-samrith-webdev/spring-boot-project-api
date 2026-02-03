package com.springdev.rentalApp.dtos;



public record UserDTO(
    Long id,
    String firstName,
    String lastName,
    String currentAddress,
    String phoneNumber,
    String email
) {}
