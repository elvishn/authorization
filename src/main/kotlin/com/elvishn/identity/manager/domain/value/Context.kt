package com.elvishn.identity.manager.domain.value

data class Context(val ip: com.elvishn.identity.manager.domain.value.IPv4,
                   val userAgent: String,
                   val device: com.elvishn.identity.manager.domain.value.Device,
                   val phoneNumber: com.elvishn.identity.manager.domain.value.PhoneNumber?)