package com.springdev.rentalApp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.util.StringUtils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;

import com.springdev.rentalApp.dtos.AuthRequest;
import com.springdev.rentalApp.dtos.AuthResponse;
import com.springdev.rentalApp.services.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    
    @PostMapping("/login")
    @Operation(
        summary = "Logiing for a existing user",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = AuthRequest.class),
                examples = @ExampleObject(
                    name = "LoginPayload",
                    value = "{\n" +
                        "  \"email\": \"user@example.com\",\n" +
                        "  \"password\": \"password123\"\n" +
                        "}"
                )
            )
        )
    )
    public ResponseEntity<?> authenticateUser(@RequestBody AuthRequest authRequest) {
        AuthResponse response = authService.login(authRequest);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + response.token())
                .body(response);
    }

    @PostMapping("/register")
        @Operation(
            summary = "Register a new user",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                required = true,
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = com.springdev.rentalApp.dtos.RegisterRequest.class),
                    examples = @ExampleObject(
                        name = "RegisterPayload",
                        value = "{\n" +
                            "  \"firstName\": \"John\",\n" +
                            "  \"lastName\": \"Doe\",\n" +
                            "  \"email\": \"john.doe@example.com\",\n" +
                            "  \"currentAddress\": \"123 Main St, Anytown, USA\",\n" +
                            "  \"password\": \"password123\"\n" +
                            "}"
                    )
                )
            )
        )
    public ResponseEntity<?> registerUser(@RequestBody com.springdev.rentalApp.dtos.RegisterRequest registerRequest) {
        try {
            AuthResponse response = authService.register(registerRequest);
            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + response.token())
                    .body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping("/logout")
        @Operation(summary = "Logout the current user")
    public ResponseEntity<?> logoutUser(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authorization) {
        String token = null;
        if (StringUtils.hasText(authorization)) {
            token = authorization.startsWith("Bearer ") ? authorization.substring(7) : authorization;
        }
        authService.logout(token);
        return ResponseEntity.ok("User logged out successfully");
    }
}
