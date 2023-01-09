package org.jesperancinha.car.lease.security

object JwtConstants {
    const val SECRET = "SECRET_KEY"
    const val EXPIRATION_TIME: Long = 900000
    const val TOKEN_PREFIX = "Bearer "
    const val HEADER_STRING = "Authorization"
}