package com.example.bank.bank.infraestructure.adapters;

import java.io.IOException;
import java.math.BigInteger;

import com.example.bank.bank.domain.data.TransactionDto;
import com.example.bank.bank.domain.port.spi.BlockchainPersistencePort;
import com.example.bank.bank.domain.contracts.Bank;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.StaticGasProvider;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.utils.Convert;
import org.web3j.utils.Convert.Unit;

import org.web3j.protocol.core.methods.request.Transaction;

@Service
public class BlockchainAdapter implements BlockchainPersistencePort {


    private Web3j web3j;

    public BlockchainAdapter() {
        this.web3j = createWeb3();
    }

    @Value("${blockchain.smartContractAddress}")
    private String CONTRACT_ADDRESS;

    @Value("${blockchain.defaultAddress}")
    private String DEFAULT_ADDRESS;


    public Bank createBank(String addressOrPk) throws Exception {

        try {

            EthBlock.Block block = getBlock();

            BigInteger gasPrice = this.web3j.ethGasPrice().send().getGasPrice();
            BigInteger gasLimit = block.getGasLimit();
            StaticGasProvider gasProvider = new StaticGasProvider(gasPrice, gasLimit);
            Bank bank = Bank.load(CONTRACT_ADDRESS, this.web3j,
                    getTransactionManager(addressOrPk), gasProvider);
            return bank;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error");
        }
    }

    public EthBlock.Block getBlock() throws IOException {
        return this.web3j.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, false).send()
                .getBlock();
    }

    public Web3j createWeb3() {
        return Web3j.build(new HttpService("HTTP://127.0.0.1:7545"));
    }

    private Credentials getCredentialFromPrivateKey(String pk) {
        return Credentials.create(pk);
    }

    private TransactionManager getTransactionManager(String addressOrPrivateKey) {
        Credentials credentials = Credentials.create(addressOrPrivateKey);
        return new RawTransactionManager(this.web3j, credentials);

    }

    //DONE
    public BigInteger getBalance(String address) {

        BigInteger balance = BigInteger.ZERO;
        try {
            balance = Convert.fromWei(this.web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST)
                    .send().getBalance().toString(), Unit.ETHER).toBigInteger();
        } catch (IOException ex) {
            throw new RuntimeException("Error whilst sending json-rpc requests", ex);
        }

        return balance;
    }

    @Override
    public TransactionDto process(TransactionDto trx) throws IOException {

        EthAccounts accounts = this.web3j.ethAccounts().send();
        EthGetTransactionCount transactionCount = this.web3j
                .ethGetTransactionCount(accounts.getAccounts().get(Math.toIntExact(trx.getFromId())),
                        DefaultBlockParameterName.LATEST)
                .send();

        Transaction transaction = Transaction.createEtherTransaction(
                accounts.getAccounts().get(Math.toIntExact(trx.getFromId())), transactionCount.getTransactionCount(),
                BigInteger.valueOf(trx.getValue()),
                BigInteger.valueOf(21_000), accounts.getAccounts().get(Math.toIntExact(trx.getToId())),
                BigInteger.valueOf(trx.getValue()));

        EthSendTransaction response = this.web3j.ethSendTransaction(transaction).send();

        // if (response.getError() != null) {
        // trx.setAccepted(false);

        // System.out.println("Tx rejected: " + response.getError().getMessage());
        // return trx;
        // }

        // trx.setAccepted(true);
        String txHash = response.getTransactionHash();
        System.out.println("Tx hash: " + txHash);

        trx.setId(txHash);
        EthGetTransactionReceipt receipt = this.web3j.ethGetTransactionReceipt(txHash).send();

        receipt.getTransactionReceipt().ifPresent(transactionReceipt -> System.out.println("Tx receipt: " +
                transactionReceipt.getCumulativeGasUsed().intValue()));

        return trx;
    }

    @Override
    public String getAccount(Long index) throws IOException {
        EthAccounts accounts = this.web3j.ethAccounts().send();
        return accounts.getAccounts().get(Math.toIntExact(index));
    }

    @Override
    public BigInteger getTotalBalances() throws Exception {
        Bank bank = createBank(this.DEFAULT_ADDRESS);
        return bank.getTotalBalances().send();
    }

    @Override
    public BigInteger getBalanceOf(String address) throws Exception {
        Bank bank = createBank(address);
        return bank.getBalanceOf(address).send();
    }

    @Override
    public TransactionReceipt addFunds(BigInteger amount, String privateKey) throws Exception {
        Bank bank = createBank(privateKey);
        return bank.addFunds(amount).send();
    }

    @Override
    public TransactionReceipt withdrawFunds(BigInteger amount, String privateKey) throws Exception {
        Bank bank = createBank(privateKey);
        return bank.withdrawFunds(amount, BigInteger.ZERO).send();
    }

    @Override
    public TransactionReceipt withdrawAllFunds( String privateKey) throws Exception {
        Bank bank = createBank(privateKey);
        return bank.withdrawAllFunds().send();
    }

    @Override
    public TransactionReceipt transferTo(BigInteger amount, String addressTo, String privateKey) throws Exception {
        Bank bank = createBank(privateKey);
        return bank.transferTo(amount, addressTo, BigInteger.ZERO).send();
    }

    @Override
    public String getAddressSC() {
        return this.CONTRACT_ADDRESS;
    }

}
