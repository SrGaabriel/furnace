package io.github.gabriel.furnace.command.impl

import dev.gaabriel.clubs.bot.impl.BotCommandContext
import dev.gaabriel.clubs.bot.util.command
import dev.gaabriel.clubs.common.struct.Command
import io.github.gabriel.furnace.command.CommandService
import io.github.gabriel.furnace.entity.RawLanguage
import io.github.gabriel.furnace.furnace
import io.github.gabriel.furnace.util.parse

val CommandService.compile: Command<BotCommandContext> get() = command("compile") {
    runs {
        val (language, code) = parseParameters(this) ?: return@runs
        val compilation = furnace.godboltClient.compiler.sendCompilationRequest(
            code = code,
            compiler = language.defaultCompiler,
            language = language.id
        )
        println(code)
        println(compilation.stdout.parse())
        reply("""
            **EXECUTION COMPLETE** *(${compilation.execTime}ms)*      
            
            ${if (compilation.stdout.isNotEmpty()) """**Output:**
            `${compilation.stdout.parse()}`""" else "No output generated."}
        """.trimIndent())
    }
}

private suspend fun parseParameters(context: BotCommandContext): Parameters? = with(context) {
    val linedContent = message.content.lines().filter(String::isNotBlank)
    val languageId = linedContent[0].trim().split(" ").filter(String::isNotBlank).drop(1).firstOrNull()?.lowercase()
        ?: return@with reply("You must provide a valid `language` parameter!").let { null }
    val language = furnace.availableLanguages.firstOrNull { it.id == languageId }
        ?: return@with reply("The provided language `$languageId` is not supported!").let { null }

    val remainingContent = linedContent
        .asSequence()
        .drop(1)
    if (remainingContent.first() != ("```") || remainingContent.last() != "```") {
        reply("Invalid codeblock provided!")
        return@with null
    }
    return Parameters(language, remainingContent.filter { it != "```" }.joinToString("\n"))
}

private data class Parameters(
    val language: RawLanguage,
    val code: String
)
