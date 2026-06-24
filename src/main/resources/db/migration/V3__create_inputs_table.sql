CREATE TABLE IF NOT EXISTS inputs(
    auth_step_id VARCHAR(255) NOT NULL,
    id_token VARCHAR(255) NOT NULL,
    verifier VARCHAR(255) NOT NULL,
    FOREIGN KEY (auth_step_id) REFERENCES auth_step(id) ON DELETE CASCADE
);