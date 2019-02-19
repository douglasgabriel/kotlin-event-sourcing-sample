package io.github.douglasgabriel.domain.exceptions

private const val MESSAGE = "The aggregate is not valid."

data class InvalidAggregateException(
        override val message: String = MESSAGE
) : Exception(message)
