package io.github.gabriel.furnace

import com.deck.core.DeckClient

private lateinit var _furnace: FurnaceBot
val furnace: FurnaceBot get() = _furnace

suspend fun main() {
    _furnace = FurnaceBot(DeckClient("MRRQZABSsU6gYdXn6mDT5pSNsSBFkUoYD4l6YZ3PccPhJLjoK+8fVNirRzEpQEBxJ5ria+VAtZ5hobPeXDKBIQ=="))
    furnace.start()
}

