package io.github.douglasgabriel.domain.data.events

sealed class UserEvent(
        open val username: String
) : Event(username)

data class UserCreatedEvent(
        override val username: String,
        val eventType: String = UserCreatedEvent::class.simpleName!!
) : UserEvent(username)

data class UserBlockStateChangedEvent(
        override val username: String,
        val blocked: Boolean,
        val eventType: String = UserBlockStateChangedEvent::class.simpleName!!
) : UserEvent(username)
