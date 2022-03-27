package io.github.gabriel.furnace.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonPrimitive

@Serializable
data class RawCompiler(
    val id: String,
    val name: String,
    val lang: String,
    val compilerType: String,
    val semver: String,
    val instructionSet: JsonPrimitive
)

@Serializable
data class RawCompilationRequest(
    val source: String,
    val compiler: String,
    val options: RawCompilationOptions,
    @SerialName("lang")
    val language: String,
    val allowStoreCodeDebug: Boolean
)

@Serializable
data class RawCompilationOptions(
    val userArguments: String = "",
    val compilerOptions: RawCompilerOptions = RawCompilerOptions(),
    val filters: RawCompilationFilters = RawCompilationFilters()
)

@Serializable
data class RawCompilerOptions(
    val skipAsm: Boolean = true,
    val executorRequest: Boolean = true,
)

@Serializable
data class RawCompilationFilters(
    val binary: Boolean? = null,
    val commentOnly: Boolean = true,
    val demangle: Boolean = true,
    val execute: Boolean = true,
    val directives: Boolean = true,
    val intel: Boolean = true,
    val labels: Boolean = true,
    val trim: Boolean = true
)

@Serializable
data class RawCompilationResponse(
    val stdout: List<RawText>,
    val stderr: List<RawText>,
    val didExecute: Boolean,
    val code: Int,
    val execTime: Long
)