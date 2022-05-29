package com.example.bank.bank.domain.service;

import java.io.IOException;
import java.util.List;

import com.example.bank.bank.domain.data.*;
import com.example.bank.bank.domain.exceptions.NoBalanceException;
import com.example.bank.bank.domain.port.api.UserServicePort;

import com.example.bank.bank.domain.port.spi.TransactionPersistencePort;
import com.example.bank.bank.domain.port.spi.UserPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserServicePort {

    @Autowired
    private UserPersistencePort userPersistencePort;

    @Autowired
    private TransactionPersistencePort transactionPersistencePort;


    public UserServiceImpl(UserPersistencePort userPersistencePort, TransactionPersistencePort transactionPersistencePort) {
        this.userPersistencePort = userPersistencePort;
        this.transactionPersistencePort = transactionPersistencePort;
    }

    @Override
    public UserDto addUser(UserDto userDto) throws IOException {
        return userPersistencePort.addUser(userDto);
    }

    @Override
    public void deleteUser(Long id) {
        userPersistencePort.deleteUser(id);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        return userPersistencePort.updateUser(userDto);
    }

    @Override
    public UserDto getUserById(Long id) {
        return userPersistencePort.getUserById(id);
    }

    @Override
    public UserDto addMoney(AmountDto amountDto) {

        UserDto user = this.getUserById(amountDto.getId());
        user.setAmount(user.getAmount() + amountDto.getAmount());
        return this.updateUser(user);
    }

    @Override
    public TransactionDto addTransaction(TransactionDto transactionDto) {

        UserDto userFrom = userPersistencePort.getUserById(transactionDto.getFromId());
        UserDto userTo = userPersistencePort.getUserById(transactionDto.getToId());
        if (transactionDto.getValue() > userFrom.getAmount()) {
            throw new NoBalanceException(userFrom.getId());
        }

        userFrom.setAmount(userFrom.getAmount() - transactionDto.getValue());
        userPersistencePort.updateUser(userFrom);
        userTo.setAmount(userTo.getAmount() + transactionDto.getValue());
        userPersistencePort.updateUser(userTo);

        TransactionEntityDto transactionEntityDto = new TransactionEntityDto(
                userFrom, userTo, transactionDto.getValue());

        return transactionPersistencePort.addTransaction(transactionEntityDto);
    }

    @Override
    public List<TransactionDto> getTransactionsByUserId(long userId) {
        return transactionPersistencePort.getTransactionsByUserId(userId);

    }

}
