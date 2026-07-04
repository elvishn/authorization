package com.elvishn.identity.manager.infrastructure.cache.repository

import com.elvishn.identity.manager.infrastructure.cache.model.AuthAttemptEntity
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
class AuthAttemptRepository(
    private val databaseClient: DatabaseClient,
) {
    fun save(authAttempt: AuthAttemptEntity): Mono<String> {
        val sql =
            """
            INSERT INTO auth_attempt 
            (id, principal, current_authentication_step, context_ip, context_user_agent, context_device_name, 
            context_device_model, context_phone_number, status, created_at, updated_at, expires_at
            ) VALUES (
            :id, :principal, :currentAuthenticationStep, :contextIp, :contextUserAgent, :contextDeviceName,
            :contextDeviceModel, :contextPhoneNumber, :status, :createdAt, :updatedAt, :expiresAt
            )
            """.trimIndent()

        return databaseClient
            .sql(sql)
            .bind("id", authAttempt.id)
            .bind("principal", authAttempt.principal)
            .bind("currentAuthenticationStep", authAttempt.currentAuthenticationStep)
            .bind("contextIp", authAttempt.contextIp)
            .bind("contextUserAgent", authAttempt.contextUserAgent)
            .bind("contextDeviceName", authAttempt.contextDeviceName)
            .bind("contextDeviceModel", authAttempt.contextDeviceModel)
            .bind("contextPhoneNumber", authAttempt.contextPhoneNumber)
            .bind("status", authAttempt.status)
            .bind("createdAt", authAttempt.createdAt)
            .bind("updatedAt", authAttempt.updatedAt)
            .bind("expiresAt", authAttempt.expiresAt)
            .then()
            .thenReturn(authAttempt.id)
    }

    fun findById(id: String): Mono<AuthAttemptEntity> {
        val sql = "SELECT * FROM auth_attempt WHERE id = :id"

        return databaseClient
            .sql(sql)
            .bind("id", id)
            .fetch()
            .one()
            .map { row ->
                AuthAttemptEntity(
                    id = row["id"] as String,
                    principal = row["principal"] as String,
                    currentAuthenticationStep = row["current_authentication_step"] as String,
                    contextIp = row["context_ip"] as String,
                    contextUserAgent = row["context_user_agent"] as String,
                    contextDeviceName = row["context_device_name"] as String,
                    contextDeviceModel = row["context_device_model"] as String,
                    contextPhoneNumber = row["context_phone_number"] as String,
                    status = row["status"] as String,
                    createdAt = row["created_at"] as Long,
                    updatedAt = row["updated_at"] as Long,
                    expiresAt = row["expires_at"] as Long,
                )
            }
    }
}
