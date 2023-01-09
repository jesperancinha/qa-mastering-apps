package org.jesperancinha.car.lease.security

import com.auth0.jwt.algorithms.Algorithm
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.jesperancinha.car.lease.model.User
import org.springframework.security.authentication.AuthenticationManager
import java.util.*

class JwtAuthenticationFilter(authenticationManager: AuthenticationManager) : UsernamePasswordAuthenticationFilter() {
    private val authenticationManager: AuthenticationManager

    init {
        this.authenticationManager = authenticationManager
        setFilterProcessesUrl("/login")
    }

    @Throws(AuthenticationException::class)
    fun attemptAuthentication(
        req: HttpServletRequest,
        res: HttpServletResponse?
    ): Authentication {
        return try {
            val credentials: User = ObjectMapper()
                .readValue<User>(req.inputStream, User::class.java)
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    credentials.username,
                    credentials.password
                )
            )
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

    @Throws(IOException::class)
    protected fun successfulAuthentication(
        req: HttpServletRequest?,
        res: HttpServletResponse,
        chain: FilterChain?,
        auth: Authentication
    ) {
        val token: String = JWT.create()
            .withSubject((auth.getPrincipal() as UserDetails).getUsername())
            .withExpiresAt(Date(System.currentTimeMillis() + JwtConstants.EXPIRATION_TIME))
            .sign(Algorithm.HMAC512(JwtConstants.SECRET.toByteArray()))
        val body: String = (auth.getPrincipal() as UserDetails).getUsername() + " " + token
        res.writer.write(body)
        res.writer.flush()
    }
}