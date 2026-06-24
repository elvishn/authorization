CREATE TABLE IF NOT EXISTS auth_attempt(
id VARCHAR(255) NOT NULL PRIMARY KEY,
principal VARCHAR(20) NOT NULL,
current_authentication_step VARCHAR(255) NOT NULL,
context_ip VARCHAR(30) NOT NULL,
context_user_agent VARCHAR(255) NOT NULL,
context_device_name VARCHAR(30) NOT NULL,
context_device_model VARCHAR(30) NOT NULL,
context_phone_number VARCHAR(15) NOT NULL,
status VARCHAR(20) NOT NULL,
created_at BIGINT NOT NULL,
updated_at BIGINT NOT NULL,
expires_at BIGINT NOT NULL
);