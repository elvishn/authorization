package com.elvishn.identity.manager.domain.value

import com.elvishn.identity.manager.domain.value.TestData.ATTEMPT_V3
import com.elvishn.identity.manager.infrastructure.cache.model.InputEntity
import org.junit.jupiter.api.Test

class InputTest {
    @Test
    fun `create InputEntity`() {
        val entity =
            Input
                .create("IdToken1", "+8 800 555 35 35")
                .toInputEntity(ATTEMPT_V3.id.toString())

        val result = InputEntity(ATTEMPT_V3.id.toString(), "IdToken1", "+8 800 555 35 35")

        assert(entity == result)
    }
}
