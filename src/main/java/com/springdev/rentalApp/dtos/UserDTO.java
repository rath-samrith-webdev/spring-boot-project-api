package com.springdev.rentalApp.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public record UserDTO(
    Long id,
    String firstName,
    String lastName,
    String email,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    LocalDate dateOfBirth
) {}
