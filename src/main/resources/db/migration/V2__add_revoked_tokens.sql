CREATE TABLE IF NOT EXISTS revoked_tokens (
    id BIGINT NOT NULL AUTO_INCREMENT,
    token_hash VARCHAR(64) NOT NULL,
    revoked_at DATETIME NOT NULL,
    expires_at DATETIME NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_revoked_tokens_token_hash (token_hash)
);
