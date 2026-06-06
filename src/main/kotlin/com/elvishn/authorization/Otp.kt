package com.elvishn.authorization

import java.security.MessageDigest
import java.security.SecureRandom

class Otp(val phoneNumber: String) {

    val code: String = (1000..9999).random().toString()
    val salt: String = SecureRandom().nextInt().toString()
    val sha256: String = (code+salt).sha256()

    fun String.sha256(): String {
        val bytes = MessageDigest.getInstance("SHA-256").digest(this.toByteArray())
        return bytes.joinToString("") { "%02x".format(it) }
    }
}
