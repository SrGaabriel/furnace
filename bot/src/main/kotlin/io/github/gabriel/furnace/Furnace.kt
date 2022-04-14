package io.github.gabriel.furnace

import io.github.gabriel.furnace.util.loadConfig

private lateinit var _furnace: FurnaceBot
val furnace: FurnaceBot get() = _furnace

suspend fun main() {
    _furnace = FurnaceBot(loadConfig())
    furnace.start()
}

