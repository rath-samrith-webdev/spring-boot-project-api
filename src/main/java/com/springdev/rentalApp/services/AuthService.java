package com.springdev.rentalApp.services;

import com.springdev.rentalApp.dtos.AuthRequest;
import com.springdev.rentalApp.dtos.AuthResponse;

public interface AuthService {
    AuthResponse login(AuthRequest authRequest);
    String register(com.springdev.rentalApp.dtos.RegisterRequest registerRequest);
}
