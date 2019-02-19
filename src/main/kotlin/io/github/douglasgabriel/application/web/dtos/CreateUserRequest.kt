package io.github.douglasgabriel.application.web.dtos

import io.github.douglasgabriel.domain.data.commands.Command
import io.github.douglasgabriel.domain.data.commands.CreateUserCommand

data class CreateUserRequest(
        val username: String
){
    fun toCommand(): Command = CreateUserCommand(
            username
    )
}
