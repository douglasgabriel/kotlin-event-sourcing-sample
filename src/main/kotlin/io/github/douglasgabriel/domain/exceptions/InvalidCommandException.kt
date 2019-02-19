package io.github.douglasgabriel.domain.exceptions

private const val MESSAGE = "The resource can not process the command."

data class InvalidCommandException(
        override val message: String = MESSAGE
) : Exception(message)
