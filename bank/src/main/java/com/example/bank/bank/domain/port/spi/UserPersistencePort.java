package com.example.bank.bank.domain.port.spi;

import com.example.bank.bank.domain.data.UserDto;

public interface UserPersistencePort {

    public UserDto addUser(UserDto userDto);

    public void deleteUser(Long id);

    public UserDto updateUser(UserDto userDto);

    public UserDto getUserById(Long id);

    public Long getLastIndex();

}
