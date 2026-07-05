package com.elvishn.identity.manager.infrastructure.cache.model

import com.elvishn.identity.manager.domain.value.Input
import com.elvishn.identity.manager.domain.value.TestData.ATTEMPT_V3
import com.elvishn.identity.manager.infrastructure.cache.model.InputEntity
import org.junit.jupiter.api.Test

class InputEntityTest {
    @Test
    fun `create Input`() {
        val entity =
            InputEntity(
                ATTEMPT_V3.id.toString(),
                "IdToken1",
                "+8 800 555 35 35",
            ).createDomainInput()

        val domain = Input.create("IdToken1", "+8 800 555 35 35")

        assert(entity == domain)
    }
}
