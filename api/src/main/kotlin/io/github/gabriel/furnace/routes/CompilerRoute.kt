package io.github.gabriel.furnace.routes

import io.github.gabriel.furnace.GodboltClient
import io.github.gabriel.furnace.entity.RawCompilationOptions
import io.github.gabriel.furnace.entity.RawCompilationRequest
import io.github.gabriel.furnace.entity.RawCompilationResponse
import io.github.gabriel.furnace.entity.RawCompiler
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

class CompilerRoute(private val client: HttpClient) {
    suspend fun sendCompilationRequest(language: String, compiler: String, code: String): RawCompilationResponse = client.post(GodboltClient.GODBOLT_API + "/compiler/$compiler/compile") {
        setBody(RawCompilationRequest(
            source = code,
            compiler = compiler,
            language = language,
            options = RawCompilationOptions(),
            allowStoreCodeDebug = true
        ))
        header(HttpHeaders.ContentType, ContentType.Application.Json)
    }.body()

    suspend fun getLanguageCompilers(language: String): List<RawCompiler> = client.get(GodboltClient.GODBOLT_API + "/compilers/$language").body()

    suspend fun getAllAvailableCompilers(): List<RawCompiler> = client.get(GodboltClient.GODBOLT_API + "/compilers").body()
}

private suspend fun main() {
    println(GodboltClient().client.post(GodboltClient.GODBOLT_API + "/compiler/kotlinc1620/compile") {
        setBody(RawCompilationRequest(
            source = """fun main() = printaln("Hello, World!")""",
            compiler = "kotlinc1620",
            language = "kotlin",
            options = RawCompilationOptions(),
            allowStoreCodeDebug = true
        ))
        header(HttpHeaders.ContentType, ContentType.Application.Json)
    }.bodyAsText())
}