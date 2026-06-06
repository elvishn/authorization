package com.elvishn.identity.manager.domain.value

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class IPv4Test {
    @Test
    fun `create valid IPv4`() {
        // given, when
        val ip = IPv4.create("123.99.0.3")

        // then
        assert(ip.value == "123.99.0.3")
    }

    @Test
    fun `create not valid IPv4`() {
        // given, when, then
        assertThrows<IllegalArgumentException> {
            IPv4.create("300.99.000.3")
        }
    }
}
