package io.github.douglasgabriel.resources.repositories

import com.mongodb.client.MongoDatabase
import io.github.douglasgabriel.application.config.ObjectMapperBuilder
import io.github.douglasgabriel.domain.data.events.Event
import io.github.douglasgabriel.domain.repositories.EventsRepository
import io.github.douglasgabriel.resources.datasources.Datasource
import org.bson.Document
import kotlin.reflect.KClass

private const val EVENTS_COLLECTION = "EVENTS_COLLECTION"

class EventsRepositoryImpl(
        private val datasource: Datasource<MongoDatabase>
) : EventsRepository {

    private val mapper by lazy { ObjectMapperBuilder().getMapper() }
    private val database by lazy { datasource.getDatabase() }

    override fun <T : Event> save(event: T) {
        database.getEventsCollectionFor(event.aggregateId)
                .insertOne(event.toDocument())
    }

    override fun <T : Event> allFor(eventType: KClass<T>, aggregateId: String): List<T> =
            database.getEventsCollectionFor(aggregateId)
                    .find()
                    .map {
                        it.convertTo(eventType)
                    }
                    .toList()

    /**
     * Converts the Mongo Document that corresponds to an [Event] subclass.
     * It converts to the more specific subclass.
     */
    private fun <T : Event> Document.convertTo(eventType: KClass<T>): T =
            eventType.sealedSubclasses.first {
                this["event_type"].toString() == it.simpleName
            }.let {
                mapper.readValue(this.toJson(), it.java)
            }

    /**
     * Converts [Event] into [Document]
     */
    private fun Event.toDocument() = Document.parse(mapper.writeValueAsString(this))

    /**
     * Gets the right collection for the given [aggregateId].
     * the collections are splitted by aggregateId to simulate an event stream idea
     */
    private fun MongoDatabase.getEventsCollectionFor(aggregateId: String) =
            this.getCollection("$EVENTS_COLLECTION/$aggregateId")

}
