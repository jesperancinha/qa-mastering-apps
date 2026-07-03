package org.jesperancinha.supermaket.idiomatic

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
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

class DomainSafePrimitivesTest {

    @Test
    fun `should build a valid user when name and email are well-formed`() {
        val (status, user) = user {
            name = "João"
            rawEmail = "joao@esperancinha.pt"
        }

        assertEquals(ValidationResult.Valid, status)
        assertTrue(user != null)
        assertEquals("João", user!!.name)
        assertEquals("joao@esperancinha.pt", user.email.value)
    }

    @Test
    fun `should reject a malformed email`() {
        val (status, user) = user {
            name = "João"
            rawEmail = "nononono"
        }

        assertTrue(status is ValidationResult.Invalid)
        assertEquals("Email is invalid", (status as ValidationResult.Invalid).reason)
        assertNull(user)
    }

    @Test
    fun `should reject a blank name`() {
        val (status, user) = user {
            name = ""
            rawEmail = "joao@esperancinha.pt"
        }

        assertTrue(status is ValidationResult.Invalid)
        assertEquals("Name is blank", (status as ValidationResult.Invalid).reason)
        assertNull(user)
    }
}