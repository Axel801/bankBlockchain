package com.example.bank.bank.domain.port.spi;

import java.util.List;

import com.example.bank.bank.domain.data.TransactionDto;
import com.example.bank.bank.domain.data.TransactionEntityDto;

public interface TransactionPersistencePort {
    public TransactionDto addTransaction(TransactionEntityDto transactionEntityDto);

    public List<TransactionDto> getTransactionsByUserId(long userId);
}
