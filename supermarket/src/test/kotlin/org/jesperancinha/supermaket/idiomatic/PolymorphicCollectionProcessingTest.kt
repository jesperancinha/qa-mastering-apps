package org.jesperancinha.supermaket.idiomatic

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
    fun `should check the polymorphic notification process`(){
        val emails = notifications.filterIsInstance<Notification.Email>()
        val smsMessages = notifications.filterIsInstance<Notification.SMS>()

        emails.forEach { println("Sending email to ${it.address}: ${it.subject}") }
        smsMessages.forEach { println("Texting ${it.number}: ${it.message}") }
        val grouped = notifications.groupBy { it::class.simpleName }

        grouped.forEach { (type, group) ->
            println("Found ${group.size} $type notification(s)")
        }
    }
}