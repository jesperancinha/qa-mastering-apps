package org.jesperancinha.car.lease.services;

import org.jesperancinha.car.lease.converters.UserConverter;
import org.jesperancinha.car.lease.dto.UserDto;
import org.jesperancinha.car.lease.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserRepository userRepository;

    public UserServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public Long saveDto(UserDto userDto) {
        userDto.setPassword(bCryptPasswordEncoder
                .encode(userDto.getPassword()));
        return userRepository.save(UserConverter.toUser(userDto)).getId();
    }
}
