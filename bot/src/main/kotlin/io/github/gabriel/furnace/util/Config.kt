package io.github.gabriel.furnace.util

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

private val CONFIG_FILE = File("config.json")
private val PRETTY_JSON = Json { prettyPrint = true }

@Serializable
data class FurnaceConfig(
    val token: String
) {
    companion object {
        val DEFAULT = FurnaceConfig(
            token = "INSERT_YOUR_TOKEN_HERE"
        )
    }
}

fun loadConfig(): FurnaceConfig {
    if (!CONFIG_FILE.exists()) {
        CONFIG_FILE.createNewFile()
        CONFIG_FILE.writeText(PRETTY_JSON.encodeToString(FurnaceConfig.DEFAULT))
        error("Config file not found. A new config file was created at ${CONFIG_FILE.absoluteFile}, modify it and then try again.")
    }
    return PRETTY_JSON.decodeFromString(CONFIG_FILE.readText())
}