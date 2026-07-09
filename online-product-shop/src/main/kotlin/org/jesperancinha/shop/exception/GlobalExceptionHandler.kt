package org.jesperancinha.shop.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.server.ServerWebExchange
import java.time.Instant

data class ApiError(
    val timestamp: Instant = Instant.now(),
    val status: Int,
    val error: String,
    val message: String?,
    val path: String?
)

/**
 * Global, reactive-safe exception handling for the WebFlux/R2DBC stack used by this
 * service. Handler methods only build a plain [ApiError] response, so they can return
 * a non-blocking [ResponseEntity] directly without ever touching a servlet-style
 * request object (which isn't available on the WebFlux request-handling threads).
 */
@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(
        ex: NoSuchElementException,
        exchange: ServerWebExchange
    ): ResponseEntity<ApiError> = errorResponse(HttpStatus.NOT_FOUND, ex.message, exchange)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgument(
        ex: IllegalArgumentException,
        exchange: ServerWebExchange
    ): ResponseEntity<ApiError> = errorResponse(HttpStatus.BAD_REQUEST, ex.message, exchange)

    @ExceptionHandler(Exception::class)
    fun handleUnexpected(
        ex: Exception,
        exchange: ServerWebExchange
    ): ResponseEntity<ApiError> = errorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.message, exchange)

    private fun errorResponse(
        status: HttpStatus,
        message: String?,
        exchange: ServerWebExchange
    ): ResponseEntity<ApiError> = ResponseEntity.status(status).body(
        ApiError(
            status = status.value(),
            error = status.reasonPhrase,
            message = message,
            path = exchange.request.path.value()
        )
    )
}
