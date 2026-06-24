package com.elvishn.identity.manager.domain.value

import com.elvishn.identity.manager.domain.entity.AuthenticationAttempt
import com.elvishn.identity.manager.domain.entity.AuthenticationStep
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

object TestData {
    // 1234:0987654321 + SHA256
    const val OTP_1234_VERIFIER = "4cfcc0abed8e05f2213c6a49e16c2b8f7149bf843c460711a87e2dcda73fc2fc:0987654321"
    val DATA  = LocalDateTime.now()
        .withHour(10)
        .withMinute(0)
        .withSecond(0)
        .withNano(0)
        .toInstant(ZoneOffset.UTC)

    val OTP_STEP =
        AuthenticationStep(
            id = UUID.fromString("0f73b20f-425e-43f8-91b3-041def33f923"),
            type = AuthenticationStepType.OTP,
            pendingInputs = listOf(Input.create("IdToken1", OTP_1234_VERIFIER)),
            nextOnSuccess = null,
            nextOnFail = null,
        )

    val CHECK_PHONE =
        AuthenticationStep(
            id = UUID.fromString("b96d1cea-13e5-4700-b469-f45944c363ab"),
            type = AuthenticationStepType.CHECK_PHONE,
            pendingInputs = listOf(Input.create("IdToken1", "+8 800 555 35 35")),
            nextOnSuccess = OTP_STEP,
            nextOnFail = null,
        )

    val IP_STEP =
        AuthenticationStep(
            id = UUID.fromString("73519425-7db1-4656-b79b-0522208476bb"),
            type = AuthenticationStepType.IP,
            pendingInputs = listOf(),
            nextOnSuccess = CHECK_PHONE,
            nextOnFail = null,
        )

    val CHROME_MOBILE_CTX =
        Context(
            ip = IPv4.create("127.0.0.1"),
            userAgent = "Chrome/v1",
            device = Device.create("Pixel", "8 Pro"),
            phoneNumber = PhoneNumber.create("+8 800 555 35 35"),
        )

    val ATTEMPT_V1 =
        AuthenticationAttempt(
            id = Id.generate(),
            principal = Principal.ANON,
            context = CHROME_MOBILE_CTX,
            previousAuthenticationSteps = emptyList(),
            currentAuthenticationStep = OTP_STEP,
            status = AttemptStatus.IN_PROGRESS,
            createdAt = DATA,
            updatedAt = DATA,
            expiresAt = DATA.plusSeconds(15_000),
        )
}
