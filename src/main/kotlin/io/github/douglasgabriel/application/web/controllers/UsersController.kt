package io.github.douglasgabriel.application.web.controllers

import io.github.douglasgabriel.application.web.dtos.BlockUserRequest
import io.github.douglasgabriel.application.web.dtos.CreateUserRequest
import io.github.douglasgabriel.domain.data.commands.Command
import io.github.douglasgabriel.domain.data.entities.User
import io.github.douglasgabriel.domain.gateways.commands.CommandsGateway
import io.javalin.Context

class UsersController(
        private val commandsGateway: CommandsGateway<User>
) {

    fun create(ctx: Context) {
        ctx.body<CreateUserRequest>()
                .toCommand()
                .send()
    }

    fun block(ctx: Context) {
        ctx.body<BlockUserRequest>()
                .toCommand()
                .send()
                .also {
                    ctx.json(it.data!!)
                }
    }

    private fun Command.send() = commandsGateway.send(this)
}
