package org.jesperancinha.supermaket.idiomatic

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

sealed class Notification {
    data class Email(val address: String, val subject: String) : Notification()
    data class SMS(val number: String, val message: String) : Notification()
    data class Push(val deviceId: String, val payload: String) : Notification()
}

val notifications: List<Notification> = listOf(
    Notification.Email("user@example.com", "Welcome!"),
    Notification.SMS("+1234567890", "Your code is 1234"),
    Notification.Push("device_42", "You've got an update"),
    Notification.Email("admin@example.com", "System alert")
)

class PolymorphicCollectionProcessingTest {

    @Test
    fun `should filter each notification type by its subclass`() {
        val emails = notifications.filterIsInstance<Notification.Email>()
        val smsMessages = notifications.filterIsInstance<Notification.SMS>()
        val pushMessages = notifications.filterIsInstance<Notification.Push>()

        assertEquals(2, emails.size)
        assertEquals(listOf("user@example.com", "admin@example.com"), emails.map { it.address })
        assertEquals(1, smsMessages.size)
        assertEquals("+1234567890", smsMessages.single().number)
        assertEquals(1, pushMessages.size)
    }

    @Test
    fun `should group notifications by their runtime type`() {
        val grouped = notifications.groupBy { it::class.simpleName }

        assertEquals(2, grouped["Email"]?.size)
        assertEquals(1, grouped["SMS"]?.size)
        assertEquals(1, grouped["Push"]?.size)
    }
}