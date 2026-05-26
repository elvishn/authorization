package com.elvishn.authorization.controller

import com.elvishn.authorization.Otp
import com.elvishn.authorization.service.OtpService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class OtpController(private val otpService: OtpService) {

    @GetMapping("/otps")
    fun getAllOtp(): List<Otp> = otpService.getAllOtp()

    @GetMapping("/otp/{phone}")
    fun getOtpByPhone(@PathVariable("phone") phone: String): Otp? =
        otpService.getByPhone(phone)

    @GetMapping("/create/{number}")
    fun create(@PathVariable number: String): Otp = otpService.generateOtp(number)

}