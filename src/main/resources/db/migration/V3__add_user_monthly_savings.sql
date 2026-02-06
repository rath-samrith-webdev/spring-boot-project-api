-- Create user_monthly_savings table
CREATE TABLE IF NOT EXISTS user_monthly_savings (
    id BIGINT NOT NULL AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    year INT NOT NULL,
    month INT NOT NULL,
    amount DOUBLE NOT NULL,
    status VARCHAR(50) NOT NULL,
    created_at VARCHAR(255),
    updated_at VARCHAR(255),
    PRIMARY KEY (id),
    INDEX idx_user_monthly_savings_user_id (user_id),
    INDEX idx_user_monthly_savings_year (year),
    INDEX idx_user_monthly_savings_user_year (user_id, year)
);

-- Insert seed data for user_monthly_savings
-- Assuming user IDs 1, 2, 3 exist from previous migrations
-- Sample data for 2025 and 2026

-- User 1 savings (consistent saver)
INSERT INTO user_monthly_savings (user_id, year, month, amount, status, created_at, updated_at) VALUES
(1, 2025, 1, 150.00, 'completed', '2025-01-31T10:00:00', '2025-01-31T10:00:00'),
(1, 2025, 2, 175.50, 'completed', '2025-02-28T10:00:00', '2025-02-28T10:00:00'),
(1, 2025, 3, 200.00, 'completed', '2025-03-31T10:00:00', '2025-03-31T10:00:00'),
(1, 2025, 4, 180.25, 'completed', '2025-04-30T10:00:00', '2025-04-30T10:00:00'),
(1, 2025, 5, 220.00, 'completed', '2025-05-31T10:00:00', '2025-05-31T10:00:00'),
(1, 2025, 6, 195.75, 'completed', '2025-06-30T10:00:00', '2025-06-30T10:00:00'),
(1, 2025, 7, 210.00, 'completed', '2025-07-31T10:00:00', '2025-07-31T10:00:00'),
(1, 2025, 8, 185.50, 'completed', '2025-08-31T10:00:00', '2025-08-31T10:00:00'),
(1, 2025, 9, 225.00, 'completed', '2025-09-30T10:00:00', '2025-09-30T10:00:00'),
(1, 2025, 10, 240.00, 'completed', '2025-10-31T10:00:00', '2025-10-31T10:00:00'),
(1, 2025, 11, 230.00, 'completed', '2025-11-30T10:00:00', '2025-11-30T10:00:00'),
(1, 2025, 12, 250.00, 'completed', '2025-12-31T10:00:00', '2025-12-31T10:00:00'),
(1, 2026, 1, 260.00, 'completed', '2026-01-31T10:00:00', '2026-01-31T10:00:00'),
(1, 2026, 2, 275.00, 'pending', '2026-02-01T10:00:00', '2026-02-01T10:00:00');

-- User 2 savings (variable saver)
INSERT INTO user_monthly_savings (user_id, year, month, amount, status, created_at, updated_at) VALUES
(2, 2025, 1, 100.00, 'completed', '2025-01-31T11:00:00', '2025-01-31T11:00:00'),
(2, 2025, 2, 120.00, 'completed', '2025-02-28T11:00:00', '2025-02-28T11:00:00'),
(2, 2025, 3, 90.00, 'completed', '2025-03-31T11:00:00', '2025-03-31T11:00:00'),
(2, 2025, 4, 150.00, 'completed', '2025-04-30T11:00:00', '2025-04-30T11:00:00'),
(2, 2025, 5, 110.00, 'completed', '2025-05-31T11:00:00', '2025-05-31T11:00:00'),
(2, 2025, 6, 130.00, 'completed', '2025-06-30T11:00:00', '2025-06-30T11:00:00'),
(2, 2025, 7, 95.00, 'completed', '2025-07-31T11:00:00', '2025-07-31T11:00:00'),
(2, 2025, 8, 140.00, 'completed', '2025-08-31T11:00:00', '2025-08-31T11:00:00'),
(2, 2025, 9, 125.00, 'completed', '2025-09-30T11:00:00', '2025-09-30T11:00:00'),
(2, 2025, 10, 160.00, 'completed', '2025-10-31T11:00:00', '2025-10-31T11:00:00'),
(2, 2025, 11, 145.00, 'completed', '2025-11-30T11:00:00', '2025-11-30T11:00:00'),
(2, 2025, 12, 180.00, 'completed', '2025-12-31T11:00:00', '2025-12-31T11:00:00'),
(2, 2026, 1, 170.00, 'completed', '2026-01-31T11:00:00', '2026-01-31T11:00:00'),
(2, 2026, 2, 190.00, 'pending', '2026-02-01T11:00:00', '2026-02-01T11:00:00');

-- User 3 savings (recent starter)
INSERT INTO user_monthly_savings (user_id, year, month, amount, status, created_at, updated_at) VALUES
(3, 2025, 10, 80.00, 'completed', '2025-10-31T12:00:00', '2025-10-31T12:00:00'),
(3, 2025, 11, 95.00, 'completed', '2025-11-30T12:00:00', '2025-11-30T12:00:00'),
(3, 2025, 12, 110.00, 'completed', '2025-12-31T12:00:00', '2025-12-31T12:00:00'),
(3, 2026, 1, 125.00, 'completed', '2026-01-31T12:00:00', '2026-01-31T12:00:00'),
(3, 2026, 2, 140.00, 'pending', '2026-02-01T12:00:00', '2026-02-01T12:00:00');
