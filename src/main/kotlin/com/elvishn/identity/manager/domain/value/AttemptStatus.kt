package com.elvishn.identity.manager.domain.value

enum class AttemptStatus {
    IN_PROGRESS,
    SUCCESS,
    BLOCKED,
    FAILED,
    SUPERSEDED;

    fun isTerminal() = this != IN_PROGRESS
}
