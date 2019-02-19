package io.github.douglasgabriel.application.config.dimodules

import io.github.douglasgabriel.domain.data.entities.User
import io.github.douglasgabriel.domain.gateways.commands.CommandsGateway
import io.github.douglasgabriel.domain.gateways.commands.implementations.CommandsGatewayImpl
import org.koin.dsl.module.module

object GatewaysModule {

    fun modules() = module {
        single { CommandsGatewayImpl<User>(get()) as CommandsGateway<User> }
    }
}
