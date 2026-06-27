package com.elvishn.identity.manager.repository

import com.elvishn.identity.manager.persistence.entity.InputEntity
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
class InputRepository(
    private val databaseClient: DatabaseClient
) {
    fun save(input: InputEntity): Mono<String> {
        val sql = """
            INSERT INTO inputs (
                auth_attempt_id, id_token, verifier
            ) VALUES (
                :authAttemptId, :idToken, :verifier
            )
        """.trimIndent()

        return databaseClient.sql(sql)
            .bind("authAttemptId", input.authAttemptId)
            .bind("idToken", input.idToken)
            .bind("verifier", input.verifier)
            .then()
            .thenReturn(input.authAttemptId)
    }

    fun findById(authAttemptId: String): Flux<InputEntity> {
        val sql = "SELECT * FROM inputs WHERE auth_attempt_id = :authAttemptId"

        return databaseClient.sql(sql)
            .bind("authAttemptId", authAttemptId)
            .fetch()
            .all()
            .map { row ->
                InputEntity(
                    authAttemptId = row["auth_attempt_id"] as String,
                    idToken = row["id_token"] as String,
                    verifier = row["verifier"] as String
                ) }
    }
}