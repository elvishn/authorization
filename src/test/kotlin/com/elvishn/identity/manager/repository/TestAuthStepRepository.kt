package com.elvishn.identity.manager.repository

import com.elvishn.identity.manager.domain.value.TestData.ATTEMPT_V1
import com.elvishn.identity.manager.domain.value.TestData.CHROME_MOBILE_CTX
import com.elvishn.identity.manager.domain.value.TestData.OTP_STEP
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.r2dbc.core.DatabaseClient
import reactor.test.StepVerifier

@SpringBootTest
class TestAuthStepRepository {

    val authStep = OTP_STEP.toAuthStepEntity(ATTEMPT_V1.id.toString())

    @Autowired
    private lateinit var authStepRepository: AuthStepRepository

    @Autowired
    private lateinit var authAttemptRepository: AuthAttemptRepository

    @Autowired
    private lateinit var databaseClient: DatabaseClient

    @BeforeEach
    fun setUp() {
        databaseClient.sql("DELETE FROM auth_step")
            .then()
            .block()
        databaseClient.sql("DELETE FROM auth_attempt")
            .then()
            .block()

        val authAttempt = ATTEMPT_V1.toAuthAttemptEntity(CHROME_MOBILE_CTX, OTP_STEP)
        authAttemptRepository.save(authAttempt).block()
    }

    @Test
    fun `should save auth step and return id`() {
        // When
        val result = authStepRepository.save(authStep)
        // Then
        StepVerifier.create(result)
            .expectNext(authStep.id)
            .verifyComplete()
    }

    @Test
    fun `should find auth step by id`() {
        val savedId = authStepRepository.save(authStep).block()
        // When
        val result = authStepRepository.findById(savedId!!)
        // Then
        StepVerifier.create(result)
            .expectNextMatches { found ->
                found.id == authStep.id &&
                        found.authAttemptId == authStep.authAttemptId &&
                        found.type == authStep.type &&
                        found.nextOnSuccess == authStep.nextOnSuccess &&
                        found.nextOnFail == authStep.nextOnFail
            }
            .verifyComplete()
    }
}