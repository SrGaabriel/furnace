package io.github.gabriel.furnace

import io.github.gabriel.furnace.routes.CompilerRoute
import io.github.gabriel.furnace.routes.LanguageRoute
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.http.*

class GodboltClient {
    val client: HttpClient = HttpClient(CIO.create()) {
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json { encodeDefaults = true; ignoreUnknownKeys = true })
            acceptContentTypes = acceptContentTypes + ContentType("application", "json")
        }
    }

    val language = LanguageRoute(client)
    val compiler = CompilerRoute(client)

    companion object {
        const val GODBOLT_API = "https://godbolt.org/api"
    }
}
