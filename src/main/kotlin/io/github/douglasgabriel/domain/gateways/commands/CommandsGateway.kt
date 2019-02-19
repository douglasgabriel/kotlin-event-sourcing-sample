package io.github.douglasgabriel.domain.gateways.commands

import io.github.douglasgabriel.domain.data.commands.Command
import io.github.douglasgabriel.domain.data.entities.Aggregate

interface CommandsGateway<T: Aggregate> {

    fun send(cmd: Command): T
}
