package com.elvishn.identity.manager.domain.value

data class Input(
    val idToken: String,
    val verifier: String,
) {
    companion object {
        fun create(
            idToken: String,
            verifier: String,
        ): Input = Input(idToken, verifier)
    }
}
