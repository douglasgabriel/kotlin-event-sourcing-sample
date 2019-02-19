package io.github.douglasgabriel.application.config.dimodules

import io.github.douglasgabriel.domain.repositories.EventsRepository
import io.github.douglasgabriel.resources.repositories.EventsRepositoryImpl
import org.koin.dsl.module.module

object RepositoriesModule {

    fun modules() = module {
        single { EventsRepositoryImpl(get()) as EventsRepository }
    }
}
