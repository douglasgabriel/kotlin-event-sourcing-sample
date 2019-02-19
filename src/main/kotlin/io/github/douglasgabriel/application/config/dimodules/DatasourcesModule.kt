package io.github.douglasgabriel.application.config.dimodules

import com.mongodb.client.MongoDatabase
import io.github.douglasgabriel.resources.datasources.Datasource
import io.github.douglasgabriel.resources.datasources.implementations.MongoDBDatasource
import org.koin.dsl.module.module

object DatasourcesModule {

    fun modules() = module {
        single { MongoDBDatasource(get()) as Datasource<MongoDatabase> }
    }

}
