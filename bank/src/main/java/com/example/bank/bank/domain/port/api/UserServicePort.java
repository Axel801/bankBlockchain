package com.example.bank.bank.domain.port.api;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import com.example.bank.bank.domain.data.AmountDto;
import com.example.bank.bank.domain.data.TransactionDto;
import com.example.bank.bank.domain.data.UserDto;
import com.example.bank.bank.domain.data.WalletDto;

public interface UserServicePort {

    public UserDto addUser(UserDto userDto) throws IOException;

    public void deleteUser(Long id);

    public UserDto updateUser(UserDto userDto);

    public UserDto getUserById(Long id);

    public UserDto addMoney(AmountDto amountDto);

    public TransactionDto addTransaction(TransactionDto transactionDto);

    public List<TransactionDto> getTransactionsByUserId(long userId);


}
