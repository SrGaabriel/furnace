package io.github.gabriel.furnace.util
fun <T> Collection<T>.paginate(page: Int, pageSize: Int) =
    take(page * pageSize) - take((page - 1) * pageSize).toSet()