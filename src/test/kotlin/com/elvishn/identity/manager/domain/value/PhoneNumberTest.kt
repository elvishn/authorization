package com.elvishn.identity.manager.domain.value

import org.junit.jupiter.api.Test

class PhoneNumberTest {

    @Test
    fun `create valid phone number`() {
        // given, when
        val phone = PhoneNumber.create("+7 9 1 1 29 63 0 1 5")

        // then
        assert(phone.data == "+79112963015")
    }
}