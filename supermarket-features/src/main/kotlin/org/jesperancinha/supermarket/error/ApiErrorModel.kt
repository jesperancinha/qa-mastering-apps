package org.jesperancinha.supermarket.error

import java.time.Instant

data class ApiError(
    val timestamp: Instant = Instant.now(),
    val status: Int,
    val error: String,
    val message: String?,
)
