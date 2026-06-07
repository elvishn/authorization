package com.elvishn.identity.manager.domain.value

import org.junit.jupiter.api.Test

class DeviceTest {
    @Test
    fun `create valid Device`() {
        // given
        val device = Device.create("     Apple ", "   17 Pro  ")

        // when, then
        assert(device.name == "Apple" && device.model == "17 Pro")
    }
}
