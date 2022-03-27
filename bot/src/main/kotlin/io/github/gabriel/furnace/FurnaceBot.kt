package io.github.gabriel.furnace

import com.deck.core.DeckClient
import io.github.gabriel.furnace.command.CommandService
import io.github.gabriel.furnace.entity.RawCompiler
import io.github.gabriel.furnace.entity.RawLanguage

class FurnaceBot(val client: DeckClient) {
    val godboltClient: GodboltClient = GodboltClient()

    val availableLanguages: MutableList<RawLanguage> = mutableListOf()
    val availableCompilers: MutableList<RawCompiler> = mutableListOf()

    suspend fun start() {
        client.login()
        CommandService().start(client)
        availableLanguages.addAll(godboltClient.language.getLanguages())
        availableCompilers.addAll(godboltClient.compiler.getAllAvailableCompilers())
    }
}