package io.github.douglasgabriel.application.config

import com.natpryce.konfig.Configuration
import com.natpryce.konfig.EnvironmentVariables
import com.natpryce.konfig.getValue
import com.natpryce.konfig.intType
import com.natpryce.konfig.stringType

class EnvironmentConfig(
        configuration: Configuration = EnvironmentVariables()
) {
    val serverPort = configuration[SERVER_PORT]

    val mongoHost = configuration[MONGO_HOST]
    val mongoPort = configuration[MONGO_PORT]
    val mongoUser = configuration[MONGO_USER]
    val mongoPassword = configuration[MONGO_PASS]
    val mongoDatabase = configuration[MONGO_DATABASE]

    companion object {
        val SERVER_PORT by intType

        val MONGO_HOST by stringType
        val MONGO_PORT by intType
        val MONGO_USER by stringType
        val MONGO_PASS by stringType
        val MONGO_DATABASE by stringType
    }

}
