package com.example.bank.bank.configuration;

import com.example.bank.bank.domain.port.spi.BlockchainPersistencePort;
import com.example.bank.bank.domain.port.spi.TransactionPersistencePort;
import com.example.bank.bank.domain.port.spi.UserPersistencePort;
import com.example.bank.bank.infraestructure.adapters.BlockchainAdapter;
import com.example.bank.bank.infraestructure.adapters.TransactionJpaAdapter;
import com.example.bank.bank.infraestructure.adapters.UserJpaAdapter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    public UserPersistencePort userPersistence() {
        return new UserJpaAdapter();
    }

//    @Bean
//    public UserServicePort userService() {
//        return new UserServiceImpl(userPersistence(), ganacheServicePort(), transactionPersistence());
//    }

    @Bean
    public BlockchainPersistencePort blockchainPersistencePort() {
        return new BlockchainAdapter();// TODO: Add in properties
    }

//    @Bean
//    public BlockchainServicePort ganacheServicePort() {
//        return new BlockchainServiceImpl(ganachePersistencePort());
//    }

    @Bean
    public TransactionPersistencePort transactionPersistence() {
        return new TransactionJpaAdapter();
    }

}