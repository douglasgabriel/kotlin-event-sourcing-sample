package io.github.douglasgabriel.domain.exceptions

private const val MESSAGE = "The resource already exists."

data class ResourceAlreadyExistsException(
        override val message: String = MESSAGE
) : Exception(message)
