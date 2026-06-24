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
                auth_step_id, id_token, verifier
            ) VALUES (
                :authStepId, :idToken, :verifier
            )
        """.trimIndent()

        return databaseClient.sql(sql)
            .bind("authStepId", input.authStepId)
            .bind("idToken", input.idToken)
            .bind("verifier", input.verifier)
            .then()
            .thenReturn(input.authStepId)
    }

    fun findById(authStepId: String): Flux<InputEntity> {
        val sql = "SELECT * FROM inputs WHERE auth_step_id = :authStepId"

        return databaseClient.sql(sql)
            .bind("authStepId", authStepId)
            .fetch()
            .all()
            .map { row ->
                InputEntity(
                    authStepId = row["auth_step_id"] as String,
                    idToken = row["id_token"] as String,
                    verifier = row["verifier"] as String
                ) }
    }
}