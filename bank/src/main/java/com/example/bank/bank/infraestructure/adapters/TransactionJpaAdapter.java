package com.example.bank.bank.infraestructure.adapters;

import java.util.List;
import java.util.stream.Collectors;

import com.example.bank.bank.domain.data.TransactionDto;
import com.example.bank.bank.domain.data.TransactionEntityDto;
import com.example.bank.bank.domain.entity.Transaction;
import com.example.bank.bank.domain.mappers.TransactionMapper;
import com.example.bank.bank.domain.port.spi.TransactionPersistencePort;
import com.example.bank.bank.infraestructure.repository.TransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class TransactionJpaAdapter implements TransactionPersistencePort {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    TransactionMapper transactionMapper;

    @Override
    @Transactional(readOnly =true)
    public List<TransactionDto> getTransactionsByUserId(long userId) {
        List<Transaction> listTransactions = transactionRepository.findAllTransactionsById(userId);

        return listTransactions.stream().map(transaction -> transactionMapper.transactionToTransactionDto(transaction))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TransactionDto addTransaction(TransactionEntityDto transactionEntityDto) {

        Transaction transaction = transactionMapper.TransactionEntityDtoToTransaction(transactionEntityDto);
        Transaction transactionSaved = transactionRepository.save(transaction);
        return transactionMapper.transactionToTransactionDto(transactionSaved);
    }

}
