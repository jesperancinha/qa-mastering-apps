package org.jesperancinha.supermaket.idiomatic

import org.junit.jupiter.api.Test


sealed class Outcome<out T> {
    data class Success<T>(val value: T) : Outcome<T>()
    data class Failure(val reason: Throwable) : Outcome<Nothing>()
}

inline fun <T, R> Outcome<T>.map(transform: (T) -> R): Outcome<R> = when (this) {
    is Outcome.Success -> Outcome.Success(transform(value))
    is Outcome.Failure -> this
}

inline fun <T> outcomeOf(block: () -> T): Outcome<T> = try {
    Outcome.Success(block())
} catch (e: Throwable) {
    Outcome.Failure(e)
}

fun fetchRemoteData(): String {
    if (Math.random() < 0.5) throw RuntimeException("Network failure")
    return "42"
}

class ErrorHandlingTest {

    @Test
    fun `should test error handling with sealed classes`(){
        val result = outcomeOf { fetchRemoteData() }
            .map { it.toInt() }
            .map { it * 2 }

        val message = when (result) {
            is Outcome.Success -> "Result: ${result.value}"
            is Outcome.Failure -> "Error: ${result.reason.message}"
        }

        println(message)
    }

}