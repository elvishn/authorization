package com.elvishn.identity.manager.domain.entity

import com.elvishn.identity.manager.domain.value.AuthenticationStepType
import com.elvishn.identity.manager.domain.value.Input
import java.util.UUID

data class AuthenticationStep(
    val id: UUID,
    val type: AuthenticationStepType,
    val pendingInputs: List<Input>,
    val nextOnSuccess: AuthenticationStep?,
    val nextOnFail: AuthenticationStep?,
)