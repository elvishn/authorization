package com.elvishn.authorization.repository

import com.elvishn.authorization.Otp
import org.springframework.stereotype.Repository

@Repository
interface OtpRepository {
    fun findAll(): List<Otp>
    fun findByPhoneNumber(phoneNumber: String): Otp?
    fun save(otp: Otp): Otp
}