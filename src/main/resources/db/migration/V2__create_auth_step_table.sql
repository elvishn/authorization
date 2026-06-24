CREATE TABLE IF NOT EXISTS auth_step(
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    auth_attempt_id VARCHAR(255) NOT NULL,
    type VARCHAR(255) NOT NULL,
    next_on_success VARCHAR(255),
    next_on_fail VARCHAR(255),
    FOREIGN KEY (auth_attempt_id) REFERENCES auth_attempt(id) ON DELETE CASCADE
);