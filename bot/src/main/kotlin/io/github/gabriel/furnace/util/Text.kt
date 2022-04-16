package io.github.gabriel.furnace.util

import io.github.gabriel.furnace.entity.RawCompilationResponse
import io.github.gabriel.furnace.entity.RawText

fun List<RawText>.parse(): String = this.joinToString("\n") { it.text }

fun RawCompilationResponse.isSuccessful(): Boolean = buildResult.code == 0