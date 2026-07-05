package com.elvishn.identity.manager.infrastructure.cache.repository

import com.elvishn.identity.manager.domain.value.Input
import com.elvishn.identity.manager.domain.value.TestData.ATTEMPT_V1
import com.elvishn.identity.manager.domain.value.TestData.CHROME_MOBILE_CTX
import com.elvishn.identity.manager.domain.value.TestData.OTP_1234_VERIFIER
import com.elvishn.identity.manager.domain.value.TestData.OTP_STEP
import com.elvishn.identity.manager.infrastructure.cache.repository.AuthAttemptRepository
import com.elvishn.identity.manager.infrastructure.cache.repository.AuthStepRepository
import com.elvishn.identity.manager.infrastructure.cache.repository.InputRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.r2dbc.core.DatabaseClient
import reactor.test.StepVerifier

@SpringBootTest
class TestInputStepRepository {
    val authStep = OTP_STEP.toAuthStepEntity(ATTEMPT_V1.id.toString())

    @Autowired
    private lateinit var authStepRepository: AuthStepRepository

    @Autowired
    private lateinit var authAttemptRepository: AuthAttemptRepository

    @Autowired
    private lateinit var inputRepository: InputRepository

    @Autowired
    private lateinit var databaseClient: DatabaseClient

    @BeforeEach
    fun setUp() {
        // Очищаем таблицы
        databaseClient
            .sql("DELETE FROM auth_step")
            .then()
            .block()
        databaseClient
            .sql("DELETE FROM auth_attempt")
            .then()
            .block()
        databaseClient
            .sql("DELETE FROM inputs")
            .then()
            .block()

        val authAttempt = ATTEMPT_V1.toAuthAttemptEntity(CHROME_MOBILE_CTX, OTP_STEP)
        authAttemptRepository.save(authAttempt).block()
        authStepRepository.save(authStep).block()
    }

    @Test
    fun `should save input and return id`() {
        // Given
        val input =
            Input
                .create("IdToken1", OTP_1234_VERIFIER)
                .toInputEntity(OTP_STEP.id.toString())
        // When
        val result = inputRepository.save(input)
        // Then
        StepVerifier
            .create(result)
            .expectNext(input.authAttemptId)
            .verifyComplete()
    }

    @Test
    fun `should find input by id`() {
        // Given
        val input =
            Input
                .create("IdToken1", OTP_1234_VERIFIER)
                .toInputEntity(OTP_STEP.id.toString())

        val savedId = inputRepository.save(input).block()
        // When
        val result = inputRepository.findById(savedId!!)
        // Then
        StepVerifier
            .create(result)
            .expectNextMatches { found ->
                found.authAttemptId == input.authAttemptId &&
                    found.idToken == input.idToken &&
                    found.verifier == input.verifier
            }.verifyComplete()
    }
}
