package io.github.gabriel.furnace

import com.deck.core.DeckClient

private lateinit var _furnace: FurnaceBot
val furnace: FurnaceBot get() = _furnace

suspend fun main() {
    _furnace = FurnaceBot(DeckClient(""))
    furnace.start()
}

