package com.elvishn.authorization.repository

import com.elvishn.authorization.Otp
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class InMemoryOtpRepository: OtpRepository{
    private val storage = ConcurrentHashMap<String, Otp>()

    override fun findAll(): List<Otp> {
        return storage.values.toList()
    }

    override fun findByPhoneNumber(phoneNumber: String): Otp? {
        return storage[phoneNumber]
    }

    override fun save(otp: Otp): Otp {
        storage[otp.phoneNumber] = otp
        return otp
    }
}