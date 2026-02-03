package com.springdev.rentalApp.services.Impls;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springdev.rentalApp.config.security.TokenHashUtils;
import com.springdev.rentalApp.entities.RevokedToken;
import com.springdev.rentalApp.repositories.RevokedTokenRepository;
import com.springdev.rentalApp.services.TokenRevocationService;

@Service
public class TokenRevocationServiceImpl implements TokenRevocationService {

    private final RevokedTokenRepository revokedTokenRepository;

    public TokenRevocationServiceImpl(RevokedTokenRepository revokedTokenRepository) {
        this.revokedTokenRepository = revokedTokenRepository;
    }

    @Override
    @Transactional
    public void revoke(String rawToken, LocalDateTime expiresAt) {
        if (rawToken == null || rawToken.isBlank()) {
            return;
        }

        String tokenHash = TokenHashUtils.sha256Hex(rawToken);
        if (revokedTokenRepository.existsByTokenHash(tokenHash)) {
            return;
        }

        RevokedToken revoked = new RevokedToken();
        revoked.setTokenHash(tokenHash);
        revoked.setRevokedAt(LocalDateTime.now());
        revoked.setExpiresAt(expiresAt);
        revokedTokenRepository.save(revoked);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isRevoked(String rawToken) {
        if (rawToken == null || rawToken.isBlank()) {
            return false;
        }
        String tokenHash = TokenHashUtils.sha256Hex(rawToken);
        return revokedTokenRepository.existsByTokenHash(tokenHash);
    }
}
