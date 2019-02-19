package io.github.douglasgabriel.domain.gateways.commands.implementations

import io.github.douglasgabriel.domain.data.commands.Command
import io.github.douglasgabriel.domain.data.entities.Aggregate
import io.github.douglasgabriel.domain.data.entities.User
import io.github.douglasgabriel.domain.data.events.UserEvent
import io.github.douglasgabriel.domain.exceptions.InvalidAggregateException
import io.github.douglasgabriel.domain.gateways.commands.CommandsGateway
import io.github.douglasgabriel.domain.services.EventsService
import io.github.douglasgabriel.domain.services.allFor

class CommandsGatewayImpl<T : Aggregate>(
        private val eventsService: EventsService,
        private val aggregateFactory: () -> T
) : CommandsGateway<T> {

    override fun send(cmd: Command): T =
            aggregateFactory()
                    .init(cmd.aggregateId)
                    .apply {
                        this.handle(cmd)
                                .also { eventsService.save(it) }
                                .also { this.apply(it) }
                    } as T

    private fun Aggregate.init(aggregateId: String) = when(this) {
        is User -> this.apply(eventsService.allFor<UserEvent>(aggregateId))
        else -> throw InvalidAggregateException()
    }

    companion object {
        inline operator fun <reified T : Aggregate> invoke(eventsService: EventsService) =
                CommandsGatewayImpl(eventsService) {
                    T::class.java.newInstance()
                }
    }
}
