package io.github.gabriel.furnace.command

import dev.gaabriel.clubs.bot.BotClubsInstance
import io.github.deck.core.DeckClient
import io.github.gabriel.furnace.command.impl.compile
import io.github.gabriel.furnace.command.impl.languages

const val PREFIX = "%"

class CommandService {
    val clubs = BotClubsInstance(PREFIX)

    suspend fun start(client: DeckClient) {
        clubs.register(compile)
        clubs.register(languages)
        clubs.start(client)
    }
}