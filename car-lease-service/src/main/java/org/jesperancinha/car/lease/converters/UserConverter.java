package org.jesperancinha.car.lease.converters;

import org.jesperancinha.car.lease.dto.UserDto;
import org.jesperancinha.car.lease.model.User;

public class UserConverter {

    public static User toUser(UserDto userDto) {
        return User
                .builder()
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .build();
    }
}
