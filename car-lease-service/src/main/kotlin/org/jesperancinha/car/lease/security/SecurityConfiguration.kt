package org.jesperancinha.car.lease.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@EnableWebSecurity
@Configuration
class SecurityConfiguration(userDetailsService: UserDetailsService, bCryptPasswordEncoder: BCryptPasswordEncoder) {
    private val userDetailsService: UserDetailsService
    private val bCryptPasswordEncoder: BCryptPasswordEncoder

    init {
        this.userDetailsService = userDetailsService
        this.bCryptPasswordEncoder = bCryptPasswordEncoder
    }

    @Bean
    fun userDetailsService(): InMemoryUserDetailsManager {
        val user: UserDetails = User.withDefaultPasswordEncoder()
            .username("user")
            .password("password")
            .roles("USER")
            .build()
        return InMemoryUserDetailsManager(user)
    }

    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http.cors().and().authorizeRequests()
            .requestMatchers("/users/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .csrf()
            .disable().build()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val source = UrlBasedCorsConfigurationSource()
        val corsConfiguration: CorsConfiguration = CorsConfiguration().applyPermitDefaultValues()
        source.registerCorsConfiguration("/**", corsConfiguration)
        return source
    }
}