package org.jesperancinha.supermaket.exception

import jakarta.servlet.http.HttpServletRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.Instant

data class ApiError(
    val timestamp: Instant = Instant.now(),
    val status: Int,
    val error: String,
    val message: String?,
)

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        request: HttpServletRequest
    ): ResponseEntity<ApiError> {
        val details = ex.bindingResult.fieldErrors
            .joinToString("; ") { "${it.field}: ${it.defaultMessage}" }
        return errorResponse(HttpStatus.BAD_REQUEST, details.ifBlank { ex.message }, request)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgument(
        ex: IllegalArgumentException,
        request: HttpServletRequest
    ): ResponseEntity<ApiError> = errorResponse(HttpStatus.BAD_REQUEST, ex.message, request)

    @ExceptionHandler(DeliveryNotFoundException::class)
    fun handleDeliveryNotFound(
        ex: DeliveryNotFoundException,
        request: HttpServletRequest
    ): ResponseEntity<ApiError> = errorResponse(HttpStatus.NOT_FOUND, ex.message, request)

    @ExceptionHandler(InvoiceNotReturnedException::class)
    fun handleInvoiceNotReturned(
        ex: InvoiceNotReturnedException,
        request: HttpServletRequest
    ): ResponseEntity<ApiError> = errorResponse(HttpStatus.BAD_GATEWAY, ex.message, request)

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
            message = "An error has been reported!",
        ).also { logger.error("Message on {} on url {}", message ?: "", request.requestURI) }
    )

    companion object {
        val logger: Logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)
    }
}
