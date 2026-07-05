package com.elvishn.identity.manager.infrastructure.cache.model

import com.elvishn.identity.manager.domain.entity.AuthenticationStep
import com.elvishn.identity.manager.domain.value.AuthenticationStepType
import java.util.UUID

data class AuthStepEntity(
    val id: String,
    val authAttemptId: String,
    val type: String,
    val nextOnSuccess: String?,
    val nextOnFail: String?,
) {
    fun createDomainStep(): AuthenticationStep =
        AuthenticationStep(
            id = UUID.fromString(this.id),
            type = AuthenticationStepType.valueOf(this.type),
            nextOnSuccess = null,
            nextOnFail = null,
        )
}
