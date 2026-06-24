package com.elvishn.identity.manager.domain.value

import com.elvishn.identity.manager.domain.value.TestData.CHECK_PHONE
import com.elvishn.identity.manager.persistence.entity.InputEntity
import org.junit.jupiter.api.Test

class InputTest {
    @Test
    fun `create InputEntity`() {
        val entity = Input.create("IdToken1", "+8 800 555 35 35")
            .toInputEntity(CHECK_PHONE.id.toString())

        val result = InputEntity("b96d1cea-13e5-4700-b469-f45944c363ab", "IdToken1", "+8 800 555 35 35")

        assert(entity == result)
    }
}