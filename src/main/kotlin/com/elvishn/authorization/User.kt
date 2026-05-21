package com.elvishn.authorization

import java.security.MessageDigest
import java.security.SecureRandom

class User {
    val phone_number: String = "+" + (10000000000..99999999999).random().toString()

    fun generateOtp(): Otp {
        val code: String = (1000..9999).random().toString()
        val salt: String = SecureRandom().nextInt().toString()
        val sha256: String = (code+salt).sha256()

        return Otp(phone_number, sha256, salt, System.currentTimeMillis())
    }

    fun String.sha256(): String {
        val bytes = MessageDigest.getInstance("SHA-256").digest(this.toByteArray())
        return bytes.joinToString("") { "%02x".format(it) }
    }
}