package com.elvishn.identity.manager.infrastructure.cache.repository

import com.elvishn.identity.manager.domain.value.TestData.ATTEMPT_V1
import com.elvishn.identity.manager.domain.value.TestData.CHROME_MOBILE_CTX
import com.elvishn.identity.manager.domain.value.TestData.OTP_STEP
import com.elvishn.identity.manager.infrastructure.cache.repository.AuthAttemptRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.r2dbc.core.DatabaseClient
import reactor.test.StepVerifier

@SpringBootTest
class TestAuthAttemptRepository {
    val authAttempt = ATTEMPT_V1.toAuthAttemptEntity(CHROME_MOBILE_CTX, OTP_STEP)

    @Autowired
    private lateinit var authAttemptRepository: AuthAttemptRepository

    @Autowired
    private lateinit var databaseClient: DatabaseClient

    @BeforeEach
    fun setUp() {
        databaseClient
            .sql("DELETE FROM auth_attempt")
            .then()
            .block()
    }

    @Test
    fun `should save auth attempt and return id`() {
        // When
        val result = authAttemptRepository.save(authAttempt)
        // Then
        StepVerifier
            .create(result)
            .expectNext(authAttempt.id)
            .verifyComplete()
    }

    @Test
    fun `should find auth attempt by id`() {
        val savedId = authAttemptRepository.save(authAttempt).block()
        // When
        val result = authAttemptRepository.findById(savedId!!)
        // Then
        StepVerifier
            .create(result)
            .expectNextMatches { found ->
                found.id == authAttempt.id &&
                    found.principal == authAttempt.principal &&
                    found.currentAuthenticationStep == authAttempt.currentAuthenticationStep &&
                    found.contextIp == authAttempt.contextIp &&
                    found.contextUserAgent == authAttempt.contextUserAgent &&
                    found.contextDeviceName == authAttempt.contextDeviceName &&
                    found.contextDeviceModel == authAttempt.contextDeviceModel &&
                    found.contextPhoneNumber == authAttempt.contextPhoneNumber &&
                    found.status == authAttempt.status &&
                    found.createdAt == authAttempt.createdAt &&
                    found.updatedAt == authAttempt.updatedAt &&
                    found.expiresAt == authAttempt.expiresAt
            }.verifyComplete()
    }
}
