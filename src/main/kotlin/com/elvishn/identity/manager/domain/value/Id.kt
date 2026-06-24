package com.elvishn.identity.manager.domain.value

import java.security.MessageDigest
import java.security.SecureRandom
import java.util.*

/**
 * Опаковая идентичность попытки аутентификации
 * и одновременно токен возобновления.
 *
 * [value] — непрозрачный секрет, который
 * отдаётся клиенту и возвращается им на каждом шаге.
 *
 * [fingerprint] — стабильная не-секретная производная (SHA-256);
 * её потребитель (ключ хранилища, безопасный лог)
 * появится на слое персиста.
 */
data class Id(
    val value: String,
) {
    fun fingerprint(): String {
        val digest =
            MessageDigest
                .getInstance("SHA-256")
                .digest(value.toByteArray(Charsets.UTF_8))
        return HexFormat
            .of()
            .formatHex(digest)
    }

    companion object {
        private const val TOKEN_BYTES = 32
        private val RANDOM = SecureRandom()
        private val ENCODER = Base64.getUrlEncoder().withoutPadding()

        fun generate(): Id {
            val bytes = ByteArray(TOKEN_BYTES)
            RANDOM.nextBytes(bytes)
            return Id(ENCODER.encodeToString(bytes))
        }

        fun of(raw: String): Id {
            require(raw.isNotBlank()) { "Id value must not be blank" }
            return Id(raw)
        }
    }

    override fun toString(): String = value
}
