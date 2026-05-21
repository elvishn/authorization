package com.elvishn.authorization

import org.springframework.data.jpa.repository.JpaRepository

interface OtpRepository : JpaRepository<Otp, String>