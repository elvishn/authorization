package com.elvishn.identity.manager.domain.value

import org.junit.jupiter.api.Test

class DeviceTest {

    @Test
    fun `create valid Device`() {
        val device = Device.create("     Apple ", "   17 Pro  ")
        assert(device.name == "Apple")
        assert(device.model == "17 Pro")
    }
}