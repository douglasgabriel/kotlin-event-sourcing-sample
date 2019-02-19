package io.github.douglasgabriel.resources.datasources.implementations

import com.mongodb.MongoCredential
import com.mongodb.ServerAddress
import com.mongodb.client.MongoDatabase
import io.github.douglasgabriel.application.config.EnvironmentConfig
import io.github.douglasgabriel.resources.datasources.Datasource
import org.litote.kmongo.KMongo

class MongoDBDatasource(
        private val environmentConfig: EnvironmentConfig
) : Datasource<MongoDatabase> {

    private val databaseClient by lazy {
        KMongo.createClient(
                listOf(
                        ServerAddress(environmentConfig.mongoHost, environmentConfig.mongoPort)
                ),
                listOf(
                        MongoCredential.createCredential(
                                environmentConfig.mongoUser,
                                environmentConfig.mongoDatabase,
                                environmentConfig.mongoPassword.toCharArray()
                        )
                )
        ).getDatabase(environmentConfig.mongoDatabase)
    }

    override fun getDatabase(): MongoDatabase = databaseClient
}
