package io.github.gabriel.furnace.entity

import kotlinx.serialization.Serializable

@Serializable
data class RawLanguage(
    val id: String,
    val name: String,
    val extensions: List<String>,
    val monaco: String,
    val defaultCompiler: String
)