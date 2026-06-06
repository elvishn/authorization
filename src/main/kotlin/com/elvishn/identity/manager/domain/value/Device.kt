package com.elvishn.identity.manager.domain.value

data class Device(
    val name: String,
    val model: String
) {
    companion object {
        fun create(name: String,
                   model: String): Device {
            return Device(name.trim(), model.trim())
        }
    }
}
