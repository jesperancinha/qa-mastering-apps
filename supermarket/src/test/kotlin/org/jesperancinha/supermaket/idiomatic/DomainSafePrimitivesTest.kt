package org.jesperancinha.supermaket.idiomatic

import org.junit.jupiter.api.Test

@JvmInline
value class Email private constructor(val value: String) {
    companion object {
        fun from(input: String): Email? =
            if (Regex("^[^@]+@[^@]+\\.[^@]+$").matches(input)) Email(input)
            else null
    }
}

sealed interface ValidationResult {
    object Valid : ValidationResult
    data class Invalid(val reason: String) : ValidationResult
}

data class User(val name: String, val email: Email)

class UserBuilder {
    var name: String? = null
    var rawEmail: String? = null

    fun build(): Pair<ValidationResult, User?> {
        val safeEmail = rawEmail?.let(Email::from)
        val safeName = name?.takeIf { it.isNotBlank() }

        return when {
            safeName == null -> ValidationResult.Invalid("Name is blank") to null
            safeEmail == null -> ValidationResult.Invalid("Email is invalid") to null
            else -> ValidationResult.Valid to User(safeName, safeEmail)
        }
    }
}

fun user(block: UserBuilder.() -> Unit): Pair<ValidationResult, User?> {
    val builder = UserBuilder().apply(block)
    return builder.build()
}

class DomainSafePrimitivesTest
{
    @Test
    fun `should check the primitive is safe`() {
        fun test1() {
            val (status, user) = user {
                name = "João"
                rawEmail = "joao@esperancinha.pt"
            }

            when (status) {
                is ValidationResult.Valid -> println("Created: $user")
                is ValidationResult.Invalid -> println("Error: ${status.reason}")
            }
        }

        fun test2() {
            val (status, user) = user {
                name = "João"
                rawEmail = "nononono"
            }

            when (status) {
                is ValidationResult.Valid -> println("Created: $user")
                is ValidationResult.Invalid -> println("Error: ${status.reason}")
            }
        }
        test1()
        test2()
    }
}