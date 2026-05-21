package com.elvishn.authorization

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table


@Entity
@Table(name="otp")
data class Otp(
    @Id
    @Column(name="phone_number", length = 12)
    val phone_number: String = "",
    @Column(name="sha256", length = 100)
    val sha256: String = "",
    @Column(name="salt", length = 50)
    val salt: String = "",
    @Column(name = "created_at", nullable = false)
    val createdAt: Long
)
