package com.elvishn.identity.manager.api

data class AuthResponse(
    val nextStep: String,
    val idTokens: List<Any>,
)
