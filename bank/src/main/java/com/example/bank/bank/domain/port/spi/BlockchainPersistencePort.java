package com.example.bank.bank.domain.port.spi;

import java.io.IOException;
import java.math.BigInteger;

import com.example.bank.bank.domain.data.TransactionDto;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

public interface BlockchainPersistencePort {

    public BigInteger getBalance(String address);

    public TransactionDto process(TransactionDto trx) throws IOException;

    public String getAccount(Long index) throws IOException;

    public BigInteger getTotalBalances() throws Exception;

    public BigInteger getBalanceOf(String address) throws Exception;

    public TransactionReceipt addFunds(BigInteger amount, String privateKey) throws Exception;

    public TransactionReceipt withdrawFunds(BigInteger amount, String privateKey) throws Exception;

    public TransactionReceipt withdrawAllFunds(String privateKey) throws Exception;

    public TransactionReceipt transferTo(BigInteger amount, String addressTo, String privateKey) throws Exception;

    public String getAddressSC();

}
