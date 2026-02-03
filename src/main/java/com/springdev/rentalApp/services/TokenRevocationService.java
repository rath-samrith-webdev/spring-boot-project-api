package com.springdev.rentalApp.services;

import java.time.LocalDateTime;

public interface TokenRevocationService {
    void revoke(String rawToken, LocalDateTime expiresAt);

    boolean isRevoked(String rawToken);
}
