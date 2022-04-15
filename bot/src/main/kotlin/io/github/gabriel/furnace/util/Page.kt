package io.github.gabriel.furnace.util

import org.jetbrains.exposed.sql.SizedIterable

data class Page<T>(
    val pages: Int,
    val items: List<T>,
)

fun <T> Iterable<T>.asPage(pages: Number): Page<T> =
    Page(pages.toInt(), toList())

inline fun <T, R> Page<T>.map(func: (T) -> R): Page<R> =
    Page(pages, items.map(func))

fun <T> SizedIterable<T>.paginate(page: Int, pageSize: Int) =
    limit(pageSize, ((page - 1) * pageSize).toLong())