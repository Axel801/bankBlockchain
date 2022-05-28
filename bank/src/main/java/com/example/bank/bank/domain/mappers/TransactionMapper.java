package com.example.bank.bank.domain.mappers;

import com.example.bank.bank.domain.data.TransactionDto;
import com.example.bank.bank.domain.data.TransactionEntityDto;
import com.example.bank.bank.domain.entity.Transaction;

import org.springframework.stereotype.Service;

@Service
public class TransactionMapper {

    public TransactionMapper(UserMapper userMapper) {
        this.userMapper = new UserMapper();
    }

    UserMapper userMapper;

    public TransactionDto transactionToTransactionDto(Transaction transaction) {

        return new TransactionDto(transaction.getId().toString(), transaction.getFrom().getId(),
                transaction.getTo().getId(),
                transaction.getAmount());
    }

    public Transaction TransactionEntityDtoToTransaction(TransactionEntityDto transactionEntityDto) {

        return new Transaction(transactionEntityDto.getId(),
                userMapper.userDtoToUser(transactionEntityDto.getFromId()),
                userMapper.userDtoToUser(transactionEntityDto.getToId()),
                transactionEntityDto.getValue());
    }

}
