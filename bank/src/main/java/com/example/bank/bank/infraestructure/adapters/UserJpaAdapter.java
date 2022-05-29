package com.example.bank.bank.infraestructure.adapters;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.bank.bank.domain.data.UserDto;
import com.example.bank.bank.domain.entity.User;
import com.example.bank.bank.domain.exceptions.ResourceNotFoundException;
import com.example.bank.bank.domain.mappers.UserMapper;
import com.example.bank.bank.domain.port.spi.UserPersistencePort;
import com.example.bank.bank.infraestructure.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

public class UserJpaAdapter implements UserPersistencePort {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Override
    @Transactional
    public UserDto addUser(UserDto userDto) {
        User user = userMapper.userDtoToUser(userDto);
        User userSaved = userRepository.save(user);
        return userMapper.userToUserDto(userSaved);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public UserDto updateUser(UserDto userDto) {
        return addUser(userDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Long getLastIndex() {
        try {
            return userRepository.findTopByOrderByIdDesc().getId() + 1L;
        } catch (Exception ex) {
            return 1L;
        }

    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getUserById(Long id) {
        return userMapper.userToUserDto(
                userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", id)));
    }

}
