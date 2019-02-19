package io.github.douglasgabriel.domain.data.entities

import io.github.douglasgabriel.domain.data.commands.Command
import io.github.douglasgabriel.domain.data.events.Event

abstract class Aggregate {

    abstract fun handle(cmd: Command): Event

    abstract fun apply(event: Event): Aggregate
    abstract fun apply(events: List<Event>): Aggregate
}
