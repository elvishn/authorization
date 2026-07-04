package com.elvishn.identity.manager.domain.value

data class IPv4(
    val value: String,
) {
    companion object {
        fun create(ip: String): IPv4 {
            require(isValid(ip)) { "Некорректный IPv4: $ip" }
            return IPv4(ip)
        }

        /**
         * Однопроходная проверка без regex и аллокаций: считаем октеты,
         * накапливая значение по цифрам и сбрасывая его на точках.
         */
        private fun isValid(ip: String): Boolean {
            if (ip.isEmpty()) {
                return false
            }
            var octets = 1
            var value = 0
            var digits = 0
            for (c in ip) {
                if (c == '.') {
                    if (digits == 0) {
                        return false
                    }
                    octets++
                    value = 0
                    digits = 0
                } else {
                    val d = c - '0'
                    if (d !in 0..9) {
                        return false
                    }
                    if (++digits > 3) {
                        return false
                    }
                    value = value * 10 + d
                    if (value > 255) {
                        return false
                    }
                }
            }

            return octets == 4 && digits != 0
        }
    }

    override fun toString(): String {
        return value
    }
}
