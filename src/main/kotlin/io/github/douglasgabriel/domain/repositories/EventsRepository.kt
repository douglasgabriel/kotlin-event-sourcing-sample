package io.github.douglasgabriel.domain.repositories

import io.github.douglasgabriel.domain.data.events.Event
import kotlin.reflect.KClass

interface EventsRepository {

    /**
     * Saves a new [event]
     */
    fun <T: Event> save(event: T)

    /**
     * Returns all registered events of [eventType] and identified by [aggregateId].
     * The [eventType] must correspond to a sealed subclass of [Event]
     */
    fun <T: Event> allFor(eventType: KClass<T>, aggregateId: String): List<T>
}
