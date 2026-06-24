package com.elvishn.identity.manager.persistence.entity

import com.elvishn.identity.manager.domain.entity.AuthenticationAttempt
import com.elvishn.identity.manager.domain.value.TestData.ATTEMPT_V1
import com.elvishn.identity.manager.domain.value.TestData.CHROME_MOBILE_CTX
import com.elvishn.identity.manager.domain.value.TestData.OTP_STEP
import org.junit.jupiter.api.Test

class AuthAttemptEntityTest {
    @Test
    fun `create AuthenticationAttempt`() {
        val entity: AuthAttemptEntity = ATTEMPT_V1.toAuthAttemptEntity(CHROME_MOBILE_CTX,
            OTP_STEP)

        val domain: AuthenticationAttempt = entity.createDomainAttempt(
            previousSteps = ATTEMPT_V1.previousAuthenticationSteps
                .map {step -> step.toAuthStepEntity(ATTEMPT_V1.id.toString())},
            currentStep = OTP_STEP.toAuthStepEntity(entity.id),
            pendingInputs = OTP_STEP.pendingInputs.map {input -> input.toInputEntity(OTP_STEP.id.toString())}
        )

        assert(ATTEMPT_V1 == domain)

    }
}