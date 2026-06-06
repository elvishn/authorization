package com.elvishn.authorization.domain.value

data class Context(val ip: IPv4,
    val userAgent: String,
    val device: Device,
    val phoneNumber: PhoneNumber?)