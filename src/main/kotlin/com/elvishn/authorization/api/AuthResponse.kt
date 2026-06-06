package com.elvishn.authorization.api

import java.util.Objects

data class AuthResponse(
    val nextStep: String,
    val idTokens: List<Objects>
)