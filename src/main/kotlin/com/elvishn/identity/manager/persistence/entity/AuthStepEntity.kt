package com.elvishn.identity.manager.persistence.entity

import com.elvishn.identity.manager.domain.entity.AuthenticationStep
import com.elvishn.identity.manager.domain.value.AuthenticationStepType
import com.elvishn.identity.manager.domain.value.Input
import java.util.*

data class AuthStepEntity(
    val id: String,
    val authAttemptId: String,
    val type: String,
    val nextOnSuccess: String?,
    val nextOnFail: String?,
) {
    fun createDomainStep(): AuthenticationStep {
        return AuthenticationStep(
            id = UUID.fromString(this.id),
            type = AuthenticationStepType.valueOf(this.type),
            nextOnSuccess = null,
            nextOnFail = null
        )
    }
}