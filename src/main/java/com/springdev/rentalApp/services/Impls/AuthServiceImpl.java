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

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
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
    public String register(com.springdev.rentalApp.dtos.RegisterRequest request) {
        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new RuntimeException("Error: Email is already in use!");
        }

        User user = new User();
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        // Wait, standard UserDTO usually doesn't return password, but for registration input it must have it.
        // I might need a separate RegisterRequest DTO or reuse UserDTO if it has password field.
        // Let's check UserDTO.

        // If UserDTO doesn't have password, I cannot use it for registration.
        // I will assume for now I need to check UserDTO.

        user.setRole("ROLE_USER");
        user.setIsActive(true);

        userRepository.save(user);

        return "User registered successfully!";
    }
}
