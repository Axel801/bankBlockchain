package com.example.bank.bank.domain.mappers;

import com.example.bank.bank.domain.data.UserDto;
import com.example.bank.bank.domain.entity.User;

import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public UserDto userToUserDto(User user) {

        return new UserDto(user.getId(), user.getName(), user.getAmount());
    }

    public User userDtoToUser(UserDto userDto) {
        return new User(userDto.getId(), userDto.getName(), userDto.getAmount());
    }


}
