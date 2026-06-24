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
    fun createDomainStep(pendingInputs: List<InputEntity>,
                         ): AuthenticationStep {
        return AuthenticationStep(
            id = UUID.fromString(this.id),
            type = AuthenticationStepType.valueOf(this.type),
            pendingInputs = pendingInputs.map { input ->
                Input.create(
                    idToken = input.idToken,
                    verifier = input.verifier
                )
            } ,
            nextOnSuccess = null,
            nextOnFail = null
        )
    }
}