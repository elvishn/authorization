package com.elvishn.identity.manager.config

import io.r2dbc.spi.ConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.r2dbc.core.DatabaseClient

@Configuration
class DatabaseConfiguration {

    @Bean
    fun databaseClient(connectionFactory: ConnectionFactory): DatabaseClient {
        return DatabaseClient.create(connectionFactory)
    }
}