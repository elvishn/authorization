package com.elvishn.authorization

class Otp(val phoneNumber: String,
          val sha256: String = "",
          val salt: String = "",
          val createdAt: Long)