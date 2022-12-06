package io.github.gabriel.furnace.command

import dev.gaabriel.clubs.bot.clubs
import io.github.deck.core.DeckClient
import io.github.gabriel.furnace.command.impl.compile
import io.github.gabriel.furnace.command.impl.languages

const val PREFIX = "%"

class CommandService {
    val clubs = clubs("%")

    fun start(client: DeckClient) {
        clubs.register(compile)
        clubs.register(languages)
        clubs.setup(client)
    }
}