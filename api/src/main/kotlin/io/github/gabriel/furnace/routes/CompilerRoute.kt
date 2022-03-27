package io.github.gabriel.furnace.routes

import io.github.gabriel.furnace.GodboltClient
import io.github.gabriel.furnace.entity.RawCompilationOptions
import io.github.gabriel.furnace.entity.RawCompilationRequest
import io.github.gabriel.furnace.entity.RawCompilationResponse
import io.github.gabriel.furnace.entity.RawCompiler
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class CompilerRoute(private val client: HttpClient) {
    suspend fun sendCompilationRequest(language: String, compiler: String, code: String): RawCompilationResponse = client.post(GodboltClient.GODBOLT_API + "/compiler/$compiler/compile") {
        body = RawCompilationRequest(
            source = code,
            compiler = compiler,
            language = language,
            options = RawCompilationOptions(),
            allowStoreCodeDebug = true
        )
        header(HttpHeaders.ContentType, ContentType.Application.Json)
    }

    suspend fun getLanguageCompilers(language: String) = client.get<List<RawCompiler>>(GodboltClient.GODBOLT_API + "/compilers/$language")

    suspend fun getAllAvailableCompilers() = client.get<List<RawCompiler>>(GodboltClient.GODBOLT_API + "/compilers")
}
