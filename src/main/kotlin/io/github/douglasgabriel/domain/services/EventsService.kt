package io.github.douglasgabriel.domain.services

import io.github.douglasgabriel.domain.data.events.Event
import kotlin.reflect.KClass

interface EventsService {

    /**
     * Saves a new event into event store.
     */
    fun <T : Event> save(event: T)

    /**
     * Returns all events for the given [eventType] and identified by [aggregateId]
     */
    fun <T : Event> allFor(eventType: KClass<T>, aggregateId: String): List<T>

}

/**
 * A shortcut function to use [EventsService.allFor].
 */
inline fun <reified T : Event> EventsService.allFor(aggregateId: String): List<T> =
        this.allFor(T::class, aggregateId)
