package io.github.douglasgabriel.domain.data.commands

sealed class UserCommand(
        override val aggregateId: String
) : Command(aggregateId)

data class CreateUserCommand(
        val username: String
): UserCommand(username)

data class ChangeUserBlockStateCommand(
        val username: String,
        val blocked: Boolean
): UserCommand(username)
