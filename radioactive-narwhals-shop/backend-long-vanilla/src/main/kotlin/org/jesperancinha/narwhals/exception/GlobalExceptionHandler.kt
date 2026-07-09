package org.jesperancinha.narwhals.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.server.ServerWebInputException
import java.time.Instant

data class ApiError(
    val timestamp: Instant = Instant.now(),
    val status: Int,
    val error: String,
    val message: String?,
    val path: String?
)

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(ServerWebInputException::class)
    fun handleBadInput(ex: ServerWebInputException, request: ServerHttpRequest): ResponseEntity<ApiError> =
        errorResponse(HttpStatus.BAD_REQUEST, ex.reason ?: ex.message, request)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgument(ex: IllegalArgumentException, request: ServerHttpRequest): ResponseEntity<ApiError> =
        errorResponse(HttpStatus.BAD_REQUEST, ex.message, request)

    @ExceptionHandler(Exception::class)
    fun handleUnexpected(ex: Exception, request: ServerHttpRequest): ResponseEntity<ApiError> =
        errorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.message, request)

    private fun errorResponse(
        status: HttpStatus,
        message: String?,
        request: ServerHttpRequest
    ): ResponseEntity<ApiError> = ResponseEntity.status(status).body(
        ApiError(
            status = status.value(),
            error = status.reasonPhrase,
            message = message,
            path = request.path.value()
        )
    )
}
