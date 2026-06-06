package com.elvishn.identity.manager.api

data class AuthRequest(
    val step: String,
    val inputs: List<Any>,
)
