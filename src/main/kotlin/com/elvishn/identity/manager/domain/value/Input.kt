package com.elvishn.identity.manager.domain.value

import com.elvishn.identity.manager.persistence.entity.InputEntity

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

    fun toInputEntity(attemptId: String): InputEntity {
        return InputEntity(
            authAttemptId = attemptId,
            idToken = this.idToken,
            verifier = this.verifier
        )
    }
}
