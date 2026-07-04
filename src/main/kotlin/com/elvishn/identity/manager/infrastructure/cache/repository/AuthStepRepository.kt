package com.elvishn.identity.manager.infrastructure.cache.repository

import com.elvishn.identity.manager.infrastructure.cache.model.AuthStepEntity
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.r2dbc.core.bind
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
class AuthStepRepository(
    private val databaseClient: DatabaseClient,
) {
    fun save(authStep: AuthStepEntity): Mono<String> {
        val sql =
            """
            INSERT INTO auth_step (
                id, auth_attempt_id, type, next_on_success, next_on_fail
            ) VALUES (
                :id, :authAttemptId, :type, :nextOnSuccess, :nextOnFail
            )
            """.trimIndent()

        return databaseClient
            .sql(sql)
            .bind("id", authStep.id)
            .bind("authAttemptId", authStep.authAttemptId)
            .bind("type", authStep.type)
            .bind("nextOnSuccess", authStep.nextOnSuccess)
            .bind("nextOnFail", authStep.nextOnFail)
            .then()
            .thenReturn(authStep.id)
    }

    fun findById(id: String): Mono<AuthStepEntity> {
        val sql = "SELECT * FROM auth_step WHERE id = :id"

        return databaseClient
            .sql(sql)
            .bind("id", id)
            .fetch()
            .one()
            .map { row ->
                AuthStepEntity(
                    id = row["id"] as String,
                    authAttemptId = row["auth_attempt_id"] as String,
                    type = row["type"] as String,
                    nextOnSuccess = row["next_on_success"] as String,
                    nextOnFail = row["next_on_fail"] as String,
                )
            }
    }
}
