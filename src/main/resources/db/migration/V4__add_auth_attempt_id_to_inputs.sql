ALTER TABLE inputs ADD COLUMN auth_attempt_id VARCHAR(255);
UPDATE inputs SET auth_attempt_id = (
    SELECT auth_attempt_id FROM auth_step WHERE auth_step.id = inputs.auth_step_id
);