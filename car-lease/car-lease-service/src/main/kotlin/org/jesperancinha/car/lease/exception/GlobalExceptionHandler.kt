package org.jesperancinha.car.lease.exception

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
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

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(
        ex: NoSuchElementException,
        request: HttpServletRequest
    ): ResponseEntity<ApiError> = errorResponse(HttpStatus.NOT_FOUND, ex.message, request)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgument(
        ex: IllegalArgumentException,
        request: HttpServletRequest
    ): ResponseEntity<ApiError> = errorResponse(HttpStatus.BAD_REQUEST, ex.message, request)

    @ExceptionHandler(Exception::class)
    fun handleUnexpected(
        ex: Exception,
        request: HttpServletRequest
    ): ResponseEntity<ApiError> = errorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.message, request)

    private fun errorResponse(
        status: HttpStatus,
        message: String?,
        request: HttpServletRequest
    ): ResponseEntity<ApiError> = ResponseEntity.status(status).body(
        ApiError(
            status = status.value(),
            error = status.reasonPhrase,
            message = message,
            path = request.requestURI
        )
    )
}
