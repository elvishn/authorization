package com.elvishn.authorization.domain.value

data class PhoneNumber(
    val data: String
) {
    companion object {
        fun create(phone: String): PhoneNumber {
            return PhoneNumber(
                data = phone.replace(" ", "")
            )
        }
    }
}
