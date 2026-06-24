package com.elvishn.identity.manager.persistence.entity

import com.elvishn.identity.manager.domain.entity.AuthenticationAttempt
import com.elvishn.identity.manager.domain.value.*
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
    val expiresAt: Long
) {

    fun createDomainAttempt(
        previousSteps: List<AuthStepEntity>,
        currentStep: AuthStepEntity,
        pendingInputs: List<InputEntity>
    ): AuthenticationAttempt {
        return AuthenticationAttempt(
            id = Id(this.id),
            principal = Principal.valueOf(this.principal),
            context = Context(
                ip = IPv4.create(contextIp),
                userAgent = contextUserAgent,
                device = Device.create(
                    name = contextDeviceName,
                    model = contextDeviceModel
                ),
                phoneNumber = PhoneNumber.create(contextPhoneNumber)
            ),
            previousAuthenticationSteps = previousSteps
                .map { step -> step.createDomainStep(pendingInputs) },
            currentAuthenticationStep = currentStep.createDomainStep(pendingInputs),
            status = AttemptStatus.valueOf(this.status),
            createdAt = Instant.ofEpochMilli(this.createdAt),
            updatedAt = Instant.ofEpochMilli(this.updatedAt),
            expiresAt = Instant.ofEpochMilli(this.expiresAt),
        )
    }


}


