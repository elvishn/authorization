package com.elvishn.authorization.service

import com.elvishn.authorization.Otp
import com.elvishn.authorization.OtpRepository
import com.elvishn.authorization.User
import org.springframework.stereotype.Service
import java.util.*

@Service
class OtpService(private val otpRepository: OtpRepository) {

    fun generateOtpForUser(): Otp {
        val user: User = User()
        val otp = user.generateOtp()
        return otpRepository.save(otp)
    }
//
    fun getAllOtp(): List<Otp> = otpRepository.findAll()

    fun getByPhone(phone: String): Optional<Otp> = otpRepository.findById(phone)

}
