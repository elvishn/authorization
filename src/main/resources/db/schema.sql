CREATE TABLE IF NOT EXISTS AuthenticationAttempt (
id VARCHAR(36),
principal VARCHAR(36),
context TEXT,
previousAuthenticationSteps VARCHAR(255),
currentAuthenticationStep VARCHAR(36),
createdAt BIGINT,
updatedAt BIGINT,
expiresAt BIGINT
);

