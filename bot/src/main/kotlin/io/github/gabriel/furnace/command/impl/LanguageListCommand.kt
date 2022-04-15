package io.github.gabriel.furnace.command.impl

import dev.gaabriel.clubs.bot.util.command
import dev.gaabriel.clubs.common.struct.arguments.optional
import dev.gaabriel.clubs.common.util.integer
import io.github.gabriel.furnace.command.CommandService
import io.github.gabriel.furnace.command.PREFIX
import io.github.gabriel.furnace.furnace
import io.github.gabriel.furnace.util.paginate
import org.jetbrains.exposed.sql.SizedCollection
import kotlin.math.ceil

private const val PAGE_SIZE: Int = 5

val CommandService.languages get() = command("languages") {
    val page by integer("page").optional()
    val languagesAsSizedCollection = SizedCollection(furnace.availableLanguages)
    runs {
        val actualPage = if ((page ?: 0) <= 0) 1 else page!!
        if (actualPage > ceil(languagesAsSizedCollection.delegate.size.toDouble() / PAGE_SIZE.toDouble())) {
            reply("You've reached the end of the list. There's nothing to be seen here.")
            return@runs
        }
        val languages = languagesAsSizedCollection.paginate(page = actualPage, pageSize = PAGE_SIZE) as SizedCollection
        reply(buildString {
            appendLine("**AVAILABLE LANGUAGES** _(page $actualPage)_")
            appendLine()
            for (language in languages) {
                appendLine("**${languagesAsSizedCollection.indexOf(language)+1})** ${language.name} (${language.id})")
            }
            appendLine()
            appendLine("**Hint:** Use `${PREFIX}language [page]` to find out more!")
        })
    }
}