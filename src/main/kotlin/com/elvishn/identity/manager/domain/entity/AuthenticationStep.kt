package com.elvishn.identity.manager.domain.entity

import com.elvishn.identity.manager.domain.value.AuthenticationStepType
import com.elvishn.identity.manager.domain.value.Input
import com.elvishn.identity.manager.persistence.entity.AuthStepEntity
import java.util.*

data class AuthenticationStep(
    val id: UUID,
    val type: AuthenticationStepType,
    val pendingInputs: List<Input>,
    val nextOnSuccess: AuthenticationStep?,
    val nextOnFail: AuthenticationStep?,
) {
    fun toAuthStepEntity(attemptId: String): AuthStepEntity {
        return AuthStepEntity(
            id = this.id.toString(),
            authAttemptId = attemptId,
            type = this.type.name,
            nextOnSuccess = this.nextOnSuccess?.id.toString(),
            nextOnFail = this.nextOnFail?.id.toString()
        )
    }
}
