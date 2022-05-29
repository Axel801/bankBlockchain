package com.example.bank.bank.domain.port.api;

import java.io.IOException;

import com.example.bank.bank.domain.data.*;

public interface BlockchainServicePort {

    public WalletDto getBalance(String address);

    public TransactionDto process(TransactionDto trx) throws IOException;

    public WalletDto getTotalBalances();

    public WalletDto getBalanceOf(String address);

    public String getAccountById(Long id);

    public TrxBlockchainDto addFunds(BlockchainDto blockchainDto);

    public TrxBlockchainDto withdrawFunds(BlockchainDto blockchainDto);

    public TrxBlockchainDto withdrawAllFunds(BlockchainDto blockchainDto);

    public TrxBlockchainDto transferTo(BankTransferDto bankTransferDto);

}
