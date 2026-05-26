package com.elvishn.authorization.service

import com.elvishn.authorization.Otp
import com.elvishn.authorization.repository.InMemoryOtpRepository
import com.elvishn.authorization.repository.OtpRepository
import org.springframework.stereotype.Service


@Service
class OtpService(private val otpRepository: OtpRepository) {

    var repoImpl: OtpRepository = InMemoryOtpRepository()

    fun generateOtp(number: String): Otp {
        val otp = Otp(number)
        return repoImpl.save(otp)
    }

    fun getAllOtp(): List<Otp> = repoImpl.findAll()

    fun getByPhone(phone: String): Otp? = repoImpl.findByPhoneNumber(phone)

}
