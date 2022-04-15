package io.github.gabriel.furnace.routes

import io.github.gabriel.furnace.GodboltClient
import io.github.gabriel.furnace.entity.RawLanguage
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class LanguageRoute(private val client: HttpClient) {
    suspend fun getLanguages(): List<RawLanguage> = client.get(GodboltClient.GODBOLT_API + "/languages?fields=id,name,extensions,monaco,defaultCompiler").body()
}