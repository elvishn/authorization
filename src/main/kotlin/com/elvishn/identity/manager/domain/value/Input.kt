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

    fun toInputEntity(stepId: String): InputEntity {
        return InputEntity(
            authStepId = stepId,
            idToken = this.idToken,
            verifier = this.verifier
        )
    }
}
