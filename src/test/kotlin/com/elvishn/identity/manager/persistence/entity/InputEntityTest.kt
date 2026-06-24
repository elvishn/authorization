package com.elvishn.identity.manager.persistence.entity

import com.elvishn.identity.manager.domain.value.Input
import org.junit.jupiter.api.Test

class InputEntityTest {
    @Test
    fun `create Input`() {
        val entity = InputEntity("b96d1cea-13e5-4700-b469-f45944c363ab",
            "IdToken1",
            "+8 800 555 35 35").createDomainInput()

        val domain = Input.create("IdToken1", "+8 800 555 35 35")

        assert(entity == domain)
    }
}