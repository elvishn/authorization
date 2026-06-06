package com.elvishn.authorization.domain.value

data class Input(val idToken: String,
    val verifier: String) {
    companion object {
        fun create(idToken: String,
                   verifier: String): Input {
            return Input(idToken, verifier)
        }
    }
}
