package io.github.douglasgabriel.domain.exceptions

private const val MESSAGE = "The resource does not exist."

data class ResourceNotExistsException(
        override val message: String = MESSAGE
) : Exception(message)
