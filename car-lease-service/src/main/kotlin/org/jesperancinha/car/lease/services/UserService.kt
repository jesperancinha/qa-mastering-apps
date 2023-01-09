package org.jesperancinha.car.lease.services

import org.jesperancinha.car.lease.model.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(bCryptPasswordEncoder: BCryptPasswordEncoder, userRepository: UserRepository) : UserDetailsService {
    private val bCryptPasswordEncoder: BCryptPasswordEncoder
    private val userRepository: UserRepository

    init {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder
        this.userRepository = userRepository
    }

    @Transactional(rollbackFor = [Exception::class])
    fun saveDto(userDto: UserDto): Long {
        userDto.setPassword(
            bCryptPasswordEncoder
                .encode(userDto.getPassword())
        )
        return userRepository.save<User>(UserConverter.toUser(userDto)).getId()
    }

    @Throws(UsernameNotFoundException::class)
    fun loadUserByUsername(username: String?): UserDetails {
        val byUsername: User = userRepository.findByUsername(username)
        return User(byUsername.username, byUsername.password, listOf())
    }
}