package com.tournament.data.golf.utils

import org.springframework.boot.test.context.TestConfiguration
import org.testcontainers.containers.JdbcDatabaseContainer
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.utility.DockerImageName

@Suppress("UtilityClassWithPublicConstructor")
@TestConfiguration
class LocalPostgresContainer {
    companion object {
        private val db = postgres("postgres:13-alpine") {
            withDatabaseName("db")
            withUsername("postgres")
            withPassword("postgres")
            withInitScript("schema.sql")
            withReuse(true)
        }

        init {
            db.start()
            System.setProperty("spring.datasource.url", db.jdbcUrl)
            System.setProperty("spring.datasource.password", db.password)
            System.setProperty("spring.datasource.username", db.username)
        }
    }
}

fun postgres(imageName: String, opts: JdbcDatabaseContainer<Nothing>.() -> Unit) =
    PostgreSQLContainer<Nothing>(DockerImageName.parse(imageName)).apply(opts)
