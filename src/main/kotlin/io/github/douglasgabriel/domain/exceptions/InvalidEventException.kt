package io.github.douglasgabriel.domain.exceptions

private const val MESSAGE = "The resource can not process the event."

data class InvalidEventException(
        override val message: String = MESSAGE
) : Exception(message)
