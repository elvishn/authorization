package com.elvishn.identity.manager.infrastructure.cache.model

import com.elvishn.identity.manager.domain.value.Input

data class InputEntity(
    val authAttemptId: String,
    val idToken: String,
    val verifier: String,
) {
    fun createDomainInput(): Input =
        Input.create(
            idToken = this.idToken,
            verifier = this.verifier,
        )
}
