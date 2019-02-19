package io.github.douglasgabriel.application.web.dtos

import io.github.douglasgabriel.domain.data.commands.ChangeUserBlockStateCommand
import io.github.douglasgabriel.domain.data.commands.Command

data class BlockUserRequest(
        val username: String,
        val blocked: Boolean
){
    fun toCommand(): Command = ChangeUserBlockStateCommand(
            username, blocked
    )
}
