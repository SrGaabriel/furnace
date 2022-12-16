package io.github.gabriel.furnace.command.impl

import io.github.srgaabriel.clubs.bot.util.command
import io.github.srgaabriel.clubs.common.util.integer
import io.github.gabriel.furnace.command.CommandService
import io.github.gabriel.furnace.command.PREFIX
import io.github.gabriel.furnace.furnace
import io.github.gabriel.furnace.util.paginate
import kotlinx.datetime.Clock
import kotlin.math.ceil

private const val PAGE_SIZE: Int = 5

val CommandService.languages get() = command("languages") {
    val page by optionalArgument("page", integer())
    executor {
        val actualPage =  (if ((page ?: 0) <= 0) 1 else page!!).coerceAtMost(ceil(furnace.availableLanguages.size.toDouble() / PAGE_SIZE.toDouble()).toInt())
        val languages = furnace.availableLanguages.paginate(page = actualPage, pageSize = PAGE_SIZE)
        replyEmbed {
            title = "Available Languages - Page $actualPage"
            color = 3092565
            description = buildString {
                appendLine("These are only **${languages.size}** languages out of **${furnace.availableLanguages.size}** in total. If you want to see more, try specifying another page!")
                appendLine()
                for (language in languages) {
                    appendLine("**${furnace.availableLanguages.indexOf(language) + 1})** ${language.name} _(${language.id})_")
                }
            }
            footer {
                text = "Use ${PREFIX}language [page] to find out more!"
            }
            timestamp = Clock.System.now()
        }
    }
}