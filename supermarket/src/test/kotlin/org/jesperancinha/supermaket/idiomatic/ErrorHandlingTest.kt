package org.jesperancinha.supermaket.idiomatic

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
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

class ErrorHandlingTest {

    @Test
    fun `should map a successful outcome through the chain`() {
        val result = outcomeOf { "42" }
            .map { it.toInt() }
            .map { it * 2 }

        assertTrue(result is Outcome.Success)
        assertEquals(84, (result as Outcome.Success).value)
    }

    @Test
    fun `should short-circuit mapping when the outcome is a failure`() {
        val result = outcomeOf<String> { throw RuntimeException("Network failure") }
            .map { it.toInt() }
            .map { it * 2 }

        assertTrue(result is Outcome.Failure)
        assertEquals("Network failure", (result as Outcome.Failure).reason.message)
    }
}