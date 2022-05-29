package com.example.bank.bank.domain.service;

import com.example.bank.bank.domain.data.*;
import com.example.bank.bank.domain.exceptions.BlockchainException;
import com.example.bank.bank.domain.port.api.BlockchainServicePort;
import com.example.bank.bank.domain.port.spi.BlockchainPersistencePort;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

import org.springframework.stereotype.Service;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

@Service
public class BlockchainServiceImpl implements BlockchainServicePort {

    private BlockchainPersistencePort blockchainPersistencePort;

    public BlockchainServiceImpl(BlockchainPersistencePort blockchainPersistencePort) {
        this.blockchainPersistencePort = blockchainPersistencePort;
    }

    @Override
    public WalletDto getBalance(String address) {

        BigInteger balance = blockchainPersistencePort.getBalance(address);
        return new WalletDto(address, balance);

    }

    @Override
    public TransactionDto process(TransactionDto trx) throws IOException {
        return blockchainPersistencePort.process(trx);
    }

    @Override
    public WalletDto getTotalBalances() {
        try {
            BigInteger balance = blockchainPersistencePort.getTotalBalances();
            String addressSC = blockchainPersistencePort.getAddressSC();
            return new WalletDto(addressSC, balance);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new BlockchainException("Error al procesar la blockchain");
        }
    }

    @Override
    public WalletDto getBalanceOf(String address) {
        try {
            BigInteger amountSC = blockchainPersistencePort.getBalanceOf(address);

            return new WalletDto(address, amountSC);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new BlockchainException("Error al procesar la blockchain");
        }
    }

    @Override
    public String getAccountById(Long id) {
        try {
            return blockchainPersistencePort.getAccount(id);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new BlockchainException("Error al procesar la blockchain");
        }

    }

    @Override
    public TrxBlockchainDto addFunds(BlockchainDto blockchainDto) {
        try {
            BigInteger bigIntegerAmount = BigDecimal.valueOf(blockchainDto.getAmountWei()).toBigInteger();
            TransactionReceipt trx = blockchainPersistencePort.addFunds(bigIntegerAmount, blockchainDto.getPrivateKey());
            BigInteger amountSC = this.getBalanceOf(blockchainDto.getAddress()).getEther();

            return new TrxBlockchainDto(trx.getTransactionHash(), amountSC, blockchainDto.getAddress());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new BlockchainException("Error al procesar blockchain");
        }

    }

    @Override
    public TrxBlockchainDto transferTo(BankTransferDto bankTransferDto) {
        try {
           TransactionReceipt trx =  blockchainPersistencePort.transferTo(bankTransferDto.getAmountWei(),bankTransferDto.getAddressTo(),bankTransferDto.getPrivateKey());
           BigInteger amountSC = this.getBalanceOf(bankTransferDto.getAddressFrom()).getEther();
            return new TrxBlockchainDto(trx.getTransactionHash(),amountSC, bankTransferDto.getAddressFrom());
        } catch (Exception ex) {
            throw new BlockchainException("Error al procesar blockchain");
        }

    }

    @Override
    public TrxBlockchainDto withdrawFunds(BlockchainDto blockchainDto) {
        try {
            BigInteger bigIntegerAmount = BigDecimal.valueOf(blockchainDto.getAmountWei()).toBigInteger();
            TransactionReceipt trx = blockchainPersistencePort.withdrawFunds(bigIntegerAmount, blockchainDto.getPrivateKey());
            BigInteger amountSC = this.getBalanceOf(blockchainDto.getAddress()).getEther();

            return new TrxBlockchainDto(trx.getTransactionHash(), amountSC, blockchainDto.getAddress());

        } catch (Exception ex) {
            throw new BlockchainException("Error al procesar blockchain");
        }
    }

    @Override
    public TrxBlockchainDto withdrawAllFunds(BlockchainDto blockchainDto) {
        try {
            TransactionReceipt trx = blockchainPersistencePort.withdrawAllFunds(blockchainDto.getPrivateKey());
            BigInteger amountSC = this.getBalanceOf(blockchainDto.getAddress()).getEther();

            return new TrxBlockchainDto(trx.getTransactionHash(), amountSC, blockchainDto.getAddress());

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new BlockchainException("Error al procesar blockchain");
        }
    }
}