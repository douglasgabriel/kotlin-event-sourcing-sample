package io.github.douglasgabriel.domain.data.entities

import io.github.douglasgabriel.domain.data.commands.ChangeUserBlockStateCommand
import io.github.douglasgabriel.domain.data.commands.Command
import io.github.douglasgabriel.domain.data.commands.CreateUserCommand
import io.github.douglasgabriel.domain.data.commands.UserCommand
import io.github.douglasgabriel.domain.data.events.Event
import io.github.douglasgabriel.domain.data.events.UserBlockStateChangedEvent
import io.github.douglasgabriel.domain.data.events.UserCreatedEvent
import io.github.douglasgabriel.domain.data.events.UserEvent
import io.github.douglasgabriel.domain.data.vos.UserData
import io.github.douglasgabriel.domain.exceptions.InvalidCommandException
import io.github.douglasgabriel.domain.exceptions.InvalidEventException
import io.github.douglasgabriel.domain.exceptions.ResourceAlreadyExistsException
import io.github.douglasgabriel.domain.exceptions.ResourceNotExistsException

class User : Aggregate() {

    var data: UserData? = null
        private set

    override fun handle(cmd: Command): Event = when(cmd) {
        is UserCommand -> when(cmd) {
            is CreateUserCommand -> this.handle(cmd)
            is ChangeUserBlockStateCommand -> this.handle(cmd)
        }
        else -> throw InvalidCommandException()
    }

    private fun handle(cmd: CreateUserCommand): UserCreatedEvent {
        this.data?.let { throw ResourceAlreadyExistsException() }

        return UserCreatedEvent(cmd.username)
    }

    private fun handle(cmd: ChangeUserBlockStateCommand): UserBlockStateChangedEvent {
        this.data ?: throw ResourceNotExistsException()

        return UserBlockStateChangedEvent(
                cmd.username,
                cmd.blocked
        )
    }

    override fun apply(event: Event): Aggregate = when (event) {
        is UserEvent -> when(event) {
            is UserCreatedEvent -> this.apply(event)
            is UserBlockStateChangedEvent -> this.apply(event)
        }
        else -> throw InvalidEventException()
    }

    override fun apply(events: List<Event>): Aggregate = this.apply { events.forEach { this.apply(it) } }

    private fun apply(event: UserCreatedEvent): User = this.apply {
        this.data = UserData(event.username, false)
    }

    private fun apply(event: UserBlockStateChangedEvent): User = this.apply {
        this.data = this.data!!.copy(blocked = event.blocked)
    }
}
