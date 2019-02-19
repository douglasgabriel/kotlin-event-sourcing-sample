package io.github.douglasgabriel.domain.services.implementations

import io.github.douglasgabriel.domain.data.events.Event
import io.github.douglasgabriel.domain.repositories.EventsRepository
import io.github.douglasgabriel.domain.services.EventsService
import kotlin.reflect.KClass

class EventsServiceImpl(
        private val eventsRepository: EventsRepository
) : EventsService {

    override fun <T : Event> save(event: T) {
        eventsRepository.save(event)
    }

    override fun <T : Event> allFor(eventType: KClass<T>, aggregateId: String): List<T> =
            eventsRepository.allFor(eventType, aggregateId)
}
