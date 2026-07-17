package org.jesperancinha.supermarket.error

import jakarta.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        request: HttpServletRequest
    ): ResponseEntity<ApiError> {
        val details = ex.bindingResult.fieldErrors
            .joinToString("; ") { "${it.field}: ${it.defaultMessage}" }
        val apiError = ApiError(
            status = HttpStatus.BAD_REQUEST.value(),
            error = HttpStatus.BAD_REQUEST.reasonPhrase,
            message = details.ifBlank { "An error has occurred!" },
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError)
            .also { reportError(ex, request) }

    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgument(
        ex: IllegalArgumentException,
        request: HttpServletRequest
    ): ResponseEntity<ApiError> {
        val apiError = ApiError(
            status = HttpStatus.BAD_REQUEST.value(),
            error = HttpStatus.BAD_REQUEST.reasonPhrase,
            message = ex.message,
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError)
            .also { reportError(ex, request) }
    }

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(
        ex: NoSuchElementException,
        request: HttpServletRequest
    ): ResponseEntity<ApiError> {
        val apiError = ApiError(
            status = HttpStatus.NOT_FOUND.value(),
            error = HttpStatus.NOT_FOUND.reasonPhrase,
            message = ex.message,
        )
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError)
            .also { reportError(ex, request) }
    }

    private fun reportError(ex: Exception, request: HttpServletRequest) {
        logger.error("Message on {} on url {}", ex.message ?: "", request.requestURI)
    }

    @ExceptionHandler(Exception::class)
    fun handleUnexpected(
        ex: Exception,
        request: HttpServletRequest
    ): ResponseEntity<ApiError> {
        val apiError = ApiError(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            error = HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase,
            message = "An error has occurred!"
        )
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError)
            .also { reportError(ex, request) }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)
    }
}
