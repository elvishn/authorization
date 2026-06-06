package com.elvishn.authorization.api

import java.util.*

data class AuthRequest(
    val step: String,
    val inputs: List<Objects>
)

//Паша запрещает