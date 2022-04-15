package io.github.gabriel.furnace

import io.github.gabriel.furnace.routes.CompilerRoute
import io.github.gabriel.furnace.routes.LanguageRoute
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

@Suppress("deprecation")
class GodboltClient {
    val client: HttpClient = HttpClient(CIO.create()) {
        install(ContentNegotiation) {
            json(Json {
                encodeDefaults = true
                ignoreUnknownKeys = true
            })
        }
        install(Logging) {
            logger = Logger.EMPTY
            level = LogLevel.INFO
        }
    }

    val language = LanguageRoute(client)
    val compiler = CompilerRoute(client)

    companion object {
        const val GODBOLT_API = "https://godbolt.org/api"
    }
}
