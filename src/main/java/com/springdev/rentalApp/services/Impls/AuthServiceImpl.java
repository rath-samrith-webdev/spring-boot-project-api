package com.springdev.rentalApp.services.Impls;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springdev.rentalApp.config.security.JwtUtils;
import com.springdev.rentalApp.dtos.AuthRequest;
import com.springdev.rentalApp.dtos.AuthResponse;
import com.springdev.rentalApp.entities.User;
import com.springdev.rentalApp.repositories.UserRepository;
import com.springdev.rentalApp.services.AuthService;
import com.springdev.rentalApp.services.TokenRevocationService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final TokenRevocationService tokenRevocationService;

    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils, TokenRevocationService tokenRevocationService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.tokenRevocationService = tokenRevocationService;
    }

    @Override
    public AuthResponse login(AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.email(), authRequest.password()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        return new AuthResponse(jwt);
    }

    @Override
    public AuthResponse register(com.springdev.rentalApp.dtos.RegisterRequest request) {
        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new RuntimeException("Error: Email is already in use!");
        }

        User user = new User();
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setEmail(request.email());
        user.setCurrentAddress(request.currentAddress());
        user.setPhoneNumber(request.phoneNumber());
        user.setPassword(passwordEncoder.encode(request.password()));

        user.setRole("ROLE_USER");
        user.setIsActive(true);

        userRepository.save(user);

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.email(), request.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        return new AuthResponse(jwt);
    }

    @Override
    public void logout(String rawToken) {
        if (rawToken == null || rawToken.isBlank()) {
            SecurityContextHolder.clearContext();
            return;
        }

        LocalDateTime expiresAt = null;
        try {
            Date exp = jwtUtils.getExpirationDateFromJwtToken(rawToken);
            if (exp != null) {
                expiresAt = exp.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            }
        } catch (Exception ignored) {
            // If token parsing fails, still clear context; revocation is best-effort.
        }

        tokenRevocationService.revoke(rawToken, expiresAt);
        SecurityContextHolder.clearContext();
    }
}
