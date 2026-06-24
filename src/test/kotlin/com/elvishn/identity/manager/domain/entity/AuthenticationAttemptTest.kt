package com.elvishn.identity.manager.domain.entity

import com.elvishn.identity.manager.domain.value.TestData.ATTEMPT_V1
import com.elvishn.identity.manager.domain.value.TestData.CHROME_MOBILE_CTX
import com.elvishn.identity.manager.domain.value.TestData.IP_STEP
import com.elvishn.identity.manager.persistence.entity.AuthAttemptEntity
import org.junit.jupiter.api.Test

class AuthenticationAttemptTest {
    @Test
    fun `create AuthAttemptEntity`() {
        val entity = ATTEMPT_V1.toAuthAttemptEntity(CHROME_MOBILE_CTX,
            IP_STEP)
        print(CHROME_MOBILE_CTX.phoneNumber.toString())
        val result = AuthAttemptEntity(
            id = ATTEMPT_V1.id.toString(),
            principal = "ANON",
            currentAuthenticationStep = IP_STEP.id.toString(),
            contextIp = "127.0.0.1",
            contextUserAgent = "Chrome/v1",
            contextDeviceName = "Pixel",
            contextDeviceModel = "8 Pro",
            contextPhoneNumber = CHROME_MOBILE_CTX.phoneNumber.toString(),
            status = "IN_PROGRESS",
            createdAt = ATTEMPT_V1.createdAt.toEpochMilli(),
            updatedAt = ATTEMPT_V1.updatedAt.toEpochMilli(),
            expiresAt = ATTEMPT_V1.expiresAt.toEpochMilli(),
        )

        assert(entity == result)
    }
}
