package org.jesperancinha.car.lease.services;

import org.jesperancinha.car.lease.converters.UserConverter;
import org.jesperancinha.car.lease.dto.UserDto;
import org.jesperancinha.car.lease.model.User;
import org.jesperancinha.car.lease.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserRepository userRepository;

    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public Long saveDto(UserDto userDto) {
        userDto.setPassword(bCryptPasswordEncoder
                .encode(userDto.getPassword()));
        return userRepository.save(UserConverter.toUser(userDto)).getId();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User byUsername = userRepository.findByUsername(username);
        return new org.springframework.security.core.userdetails.User(byUsername.getUsername(), byUsername.getPassword(), List.of());
    }
}
