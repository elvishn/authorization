package com.elvishn.identity.manager.infrastructure.cache.model

import com.elvishn.identity.manager.domain.entity.AuthenticationAttempt
import com.elvishn.identity.manager.domain.value.TestData.ATTEMPT_V1
import com.elvishn.identity.manager.domain.value.TestData.CHROME_MOBILE_CTX
import com.elvishn.identity.manager.domain.value.TestData.OTP_STEP
import com.elvishn.identity.manager.infrastructure.cache.model.AuthAttemptEntity
import org.junit.jupiter.api.Test

class AuthAttemptEntityTest {
    @Test
    fun `create AuthenticationAttempt`() {
        val entity: AuthAttemptEntity =
            ATTEMPT_V1.toAuthAttemptEntity(
                CHROME_MOBILE_CTX,
                OTP_STEP,
            )

        val domain: AuthenticationAttempt =
            entity.createDomainAttempt(
                previousSteps =
                    ATTEMPT_V1.previousAuthenticationSteps
                        .map { step -> step.toAuthStepEntity(ATTEMPT_V1.id.toString()) },
                currentStep = OTP_STEP.toAuthStepEntity(entity.id),
                pendingInputs = ATTEMPT_V1.pendingInputs.map { input -> input.toInputEntity(entity.id) },
            )

        assert(ATTEMPT_V1 == domain)
    }
}
