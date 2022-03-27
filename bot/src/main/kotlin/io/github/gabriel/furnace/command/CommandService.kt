package io.github.gabriel.furnace.command

import com.deck.core.DeckClient
import dev.gaabriel.clubs.bot.BotClubsInstance
import io.github.gabriel.furnace.command.impl.compile

class CommandService {
    val clubs = BotClubsInstance("-")

    suspend fun start(client: DeckClient) {
        clubs.register(compile)
        clubs.start(client)
    }
}