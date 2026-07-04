package com.elvishn.identity.manager.domain.entity

import com.elvishn.identity.manager.domain.value.AuthenticationStepType
import com.elvishn.identity.manager.infrastructure.cache.model.AuthStepEntity
import java.util.UUID

data class AuthenticationStep(
    val id: UUID,
    val type: AuthenticationStepType,
    val nextOnSuccess: AuthenticationStep?,
    val nextOnFail: AuthenticationStep?,
) {
    fun toAuthStepEntity(attemptId: String): AuthStepEntity =
        AuthStepEntity(
            id = this.id.toString(),
            authAttemptId = attemptId,
            type = this.type.name,
            nextOnSuccess = this.nextOnSuccess?.id.toString(),
            nextOnFail = this.nextOnFail?.id.toString(),
        )
}
