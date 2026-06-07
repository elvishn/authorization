package com.elvishn.identity.manager.domain.entity

import com.elvishn.identity.manager.domain.value.AttemptStatus
import com.elvishn.identity.manager.domain.value.Context
import com.elvishn.identity.manager.domain.value.Id
import com.elvishn.identity.manager.domain.value.Principal
import com.elvishn.identity.manager.domain.value.StepOutcome
import java.time.Instant

data class AuthenticationAttempt(
    val id: Id,
    val principal: Principal,
    val context: Context,
    val previousAuthenticationSteps: List<AuthenticationStep>,
    val currentAuthenticationStep: AuthenticationStep,
    val status: AttemptStatus,
    val createdAt: Instant,
    val updatedAt: Instant,
    val expiresAt: Instant
) {

    fun isExpired(now: Instant) = !now.isBefore(expiresAt)

    /**
     * Продвигает попытку по графу шагов согласно исходу текущего шага.
     * Текущий шаг уходит в историю, следующий берётся
     * из [AuthenticationStep.nextOnSuccess]
     * или [AuthenticationStep.nextOnFail].
     * Если следующего шага нет — попытка завершается
     * терминальным статусом ([AttemptStatus.SUCCESS] / [AttemptStatus.FAILED]).
     */
    fun advance(outcome: StepOutcome): AuthenticationAttempt {
        check(status == AttemptStatus.IN_PROGRESS) {
            "Cannot advance a finished attempt: $status"
        }
        val next = when (outcome) {
            StepOutcome.SUCCESS -> currentAuthenticationStep.nextOnSuccess
            StepOutcome.FAIL -> currentAuthenticationStep.nextOnFail
        }
        return if (next == null) {
            copy(
                status = if (outcome == StepOutcome.SUCCESS) {
                    AttemptStatus.SUCCESS
                } else {
                    AttemptStatus.FAILED
                }
            )
        } else {
            copy(
                previousAuthenticationSteps = previousAuthenticationSteps + currentAuthenticationStep,
                currentAuthenticationStep = next
            )
        }
    }

    /** Закрывает незавершённую попытку при
     * старте новой для того же субъекта («одна активная»). */
    fun markSuperseded(): AuthenticationAttempt {
        check(status == AttemptStatus.IN_PROGRESS) {
            "Only an in-progress attempt can be superseded: $status"
        }
        return copy(status = AttemptStatus.SUPERSEDED)
    }
}
