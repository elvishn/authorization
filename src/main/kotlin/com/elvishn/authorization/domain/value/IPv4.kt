package com.elvishn.authorization.domain.value

data class IPv4(
    val value: String
) {
    companion object {
        private val IPV4_REGEX = Regex(
            "^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})$"
        )

        fun create(ip: String): IPv4 {
            val match = IPV4_REGEX.matchEntire(ip)
            val isValid = match != null && match.groupValues.drop(1).all {
                it.toInt() in 0..255
            }
            require(isValid) { "Некорректный  IPv4: $ip" }

            return IPv4(ip)
        }
    }
}

