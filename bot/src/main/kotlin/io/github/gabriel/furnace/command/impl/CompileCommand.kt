package io.github.gabriel.furnace.command.impl

import io.github.srgaabriel.clubs.bot.impl.BotCommandContext
import io.github.srgaabriel.clubs.bot.util.command
import io.github.srgaabriel.clubs.common.struct.Command
import io.github.gabriel.furnace.command.CommandService
import io.github.gabriel.furnace.entity.RawCompilationResponse
import io.github.gabriel.furnace.entity.RawLanguage
import io.github.gabriel.furnace.furnace
import io.github.gabriel.furnace.util.isSuccessful
import io.github.gabriel.furnace.util.parse
import kotlinx.datetime.Clock

val CommandService.compile: Command<BotCommandContext> get() = command("compile") {
    executor {
        val (language, code) = parseParameters(this) ?: return@executor

        message.addReaction(1596569)
        val compilation = furnace.godboltClient.compiler.sendCompilationRequest(
            code = code,
            compiler = language.defaultCompiler,
            language = language.id
        )
        message.removeReaction(1596569)
        replyAccordinlyToCompilationResults(language, compilation)
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

private suspend fun BotCommandContext.replyAccordinlyToCompilationResults(
    language: RawLanguage,
    compilation: RawCompilationResponse
) {
    if (compilation.isSuccessful()) {
        val output = compilation.stdout.parse()
        val outputMessage = if (output.isEmpty()) "**Output:** No output was generated" else
            """
                **Output:**
                ```${compilation.stdout.parse()}```
            """.trimIndent()

        replyEmbed {
            title = "Successful Compilation"
            color = 65377
            description = """
                        The `${language.name}` code was compiled successfully using the `${language.defaultCompiler}` compiler.
                        
                        $outputMessage
                    """.trimIndent()
            footer {
                text = "Executed in ${compilation.execTime}ms"
            }
            timestamp = Clock.System.now()
        }
    } else {
        replyEmbed {
            title = "Failed Compilation"
            color = 15269888
            description = """
                        The `${language.name}` code couldn't be compiled with the `${language.defaultCompiler}` compiler.
                        
                        **Error:**
                        ```${compilation.buildResult.stderr.parse()}```
                    """.trimIndent()
            footer {
                text = "Ended with code ${compilation.code}"
            }
            timestamp = Clock.System.now()
        }
    }
}

private data class Parameters(
    val language: RawLanguage,
    val code: String
)
