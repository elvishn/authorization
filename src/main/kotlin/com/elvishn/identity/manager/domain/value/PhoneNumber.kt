package com.elvishn.identity.manager.domain.value

data class PhoneNumber private constructor(
    val data: String
) {
    override fun toString(): String = data
    companion object {
        fun create(phone: String): PhoneNumber =
            PhoneNumber(
                data = phone.replace(" ", ""),
            )
    }
}
