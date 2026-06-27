package com.elvishn.identity.manager.persistence.entity

import com.elvishn.identity.manager.domain.value.Input

data class InputEntity(
    val authAttemptId: String,
    val idToken: String,
    val verifier: String,
) {
    fun createDomainInput(): Input {
        return Input.create(
            idToken = this.idToken,
            verifier = this.verifier
        )
    }
}