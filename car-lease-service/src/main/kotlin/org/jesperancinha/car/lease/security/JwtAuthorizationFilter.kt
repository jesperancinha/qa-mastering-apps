package org.jesperancinha.car.lease.security

import com.auth0.jwt.algorithms.Algorithm
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager

class JwtAuthorizationFilter(authManager: AuthenticationManager?) : BasicAuthenticationFilter(authManager) {
    @Throws(IOException::class, ServletException::class)
    protected fun doFilterInternal(
        req: HttpServletRequest,
        res: HttpServletResponse?,
        chain: FilterChain
    ) {
        val header = req.getHeader(JwtConstants.HEADER_STRING)
        if (header == null || !header.startsWith(JwtConstants.TOKEN_PREFIX)) {
            chain.doFilter(req, res)
            return
        }
        val authentication: UsernamePasswordAuthenticationToken? = getAuthentication(req)
        SecurityContextHolder.getContext().setAuthentication(authentication)
        chain.doFilter(req, res)
    }

    private fun getAuthentication(request: HttpServletRequest): UsernamePasswordAuthenticationToken? {
        val token = request.getHeader(JwtConstants.HEADER_STRING)
        if (token != null) {
            val user: String = JWT.require(Algorithm.HMAC512(JwtConstants.SECRET.toByteArray()))
                .build()
                .verify(token.replace(JwtConstants.TOKEN_PREFIX, ""))
                .getSubject()
            return if (user != null) {
                UsernamePasswordAuthenticationToken(user, null, ArrayList<E>())
            } else null
        }
        return null
    }
}