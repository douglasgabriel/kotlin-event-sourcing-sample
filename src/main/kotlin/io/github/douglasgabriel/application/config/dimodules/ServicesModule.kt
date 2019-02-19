package io.github.douglasgabriel.application.config.dimodules

import io.github.douglasgabriel.domain.services.EventsService
import io.github.douglasgabriel.domain.services.implementations.EventsServiceImpl
import org.koin.dsl.module.module

object ServicesModule {

    fun modules() = module {
        single { EventsServiceImpl(get()) as EventsService }
    }
}
