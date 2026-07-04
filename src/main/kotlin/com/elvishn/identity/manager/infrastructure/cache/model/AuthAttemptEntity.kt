package com.elvishn.identity.manager.infrastructure.cache.model

import com.elvishn.identity.manager.domain.entity.AuthenticationAttempt
import com.elvishn.identity.manager.domain.value.AttemptStatus
import com.elvishn.identity.manager.domain.value.Context
import com.elvishn.identity.manager.domain.value.Device
import com.elvishn.identity.manager.domain.value.IPv4
import com.elvishn.identity.manager.domain.value.Id
import com.elvishn.identity.manager.domain.value.Input
import com.elvishn.identity.manager.domain.value.PhoneNumber
import com.elvishn.identity.manager.domain.value.Principal
import java.time.Instant

data class AuthAttemptEntity(
    val id: String,
    val principal: String,
    val currentAuthenticationStep: String,
    val contextIp: String,
    val contextUserAgent: String,
    val contextDeviceName: String,
    val contextDeviceModel: String,
    val contextPhoneNumber: String,
    val status: String,
    val createdAt: Long,
    val updatedAt: Long,
    val expiresAt: Long,
) {
    fun createDomainAttempt(
        previousSteps: List<AuthStepEntity>,
        currentStep: AuthStepEntity,
        pendingInputs: List<InputEntity>,
    ): AuthenticationAttempt =
        AuthenticationAttempt(
            id = Id(this.id),
            principal = Principal.valueOf(this.principal),
            context =
                Context(
                    ip = IPv4.create(contextIp),
                    userAgent = contextUserAgent,
                    device =
                        Device.create(
                            name = contextDeviceName,
                            model = contextDeviceModel,
                        ),
                    phoneNumber = PhoneNumber.create(contextPhoneNumber),
                ),
            previousAuthenticationSteps =
                previousSteps
                    .map { step -> step.createDomainStep() },
            currentAuthenticationStep = currentStep.createDomainStep(),
            pendingInputs =
                pendingInputs.map { input ->
                    Input.create(
                        idToken = input.idToken,
                        verifier = input.verifier,
                    )
                },
            status = AttemptStatus.valueOf(this.status),
            createdAt = Instant.ofEpochMilli(this.createdAt),
            updatedAt = Instant.ofEpochMilli(this.updatedAt),
            expiresAt = Instant.ofEpochMilli(this.expiresAt),
        )
}
