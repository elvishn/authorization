package com.elvishn.identity.manager.domain.value

import com.elvishn.identity.manager.infrastructure.cache.model.InputEntity

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

    fun toInputEntity(attemptId: String): InputEntity =
        InputEntity(
            authAttemptId = attemptId,
            idToken = this.idToken,
            verifier = this.verifier,
        )
}
