package com.codermp.core.domain.utils

/**
 * Sealed interface that represents a result of a data operation.
 */
sealed interface Result<out D, out E: Error> {
    data class Success<out D>(val data: D): Result<D, Nothing>
    data class Error<out E: com.codermp.core.domain.utils.Error>(val error: E): Result<Nothing, E>
}

/**
 * Extension function that maps a [Result] of type [T] to a [Result] of type [R].
 * @param map The function to map the data.
 * @return The mapped [Result].
 */
inline fun <T, E: Error, R> Result<T, E>.map(map: (T) -> R): Result<R, E> {
    return when(this) {
        is Result.Error -> Result.Error(error)
        is Result.Success -> Result.Success(map(data))
    }
}

/**
 * Extension function that takes a [Result] of type [T] and returns it as an [EmptyResult].
 */
fun <T, E: Error> Result<T, E>.asEmptyDataResult(): EmptyResult<E> {
    return map {  }
}

typealias EmptyResult<E> = Result<Unit, E>