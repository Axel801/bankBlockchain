package com.example.bank.bank.application.controller;

import com.example.bank.bank.domain.data.*;
import com.example.bank.bank.domain.port.api.BlockchainServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@RestController
@RequestMapping("/blockchain")
public class BlockchainController {

    @Autowired
    private BlockchainServicePort blockchainServicePort;


    //Address added for connection to blockchain
    @GetMapping("/bank-balance")
    public WalletDto getTotalBalanceGanache() {
        return blockchainServicePort.getTotalBalances();
    }

    @GetMapping("/user-balance/{address}")
    public WalletDto getUserBalance(@NotNull @NotEmpty @PathVariable(name = "address") String address) {
        return blockchainServicePort.getBalance(address);
    }

    @GetMapping("/bank/user-balance/{address}")
    public WalletDto getBankUserBalance(@NotNull @NotEmpty @PathVariable(name = "address") String address) {
        return blockchainServicePort.getBalanceOf(address);
    }

    @PostMapping("/bank/add-money")
    public TrxBlockchainDto addMoneyGanache(@RequestBody BlockchainDto blockchainDto) {
        return blockchainServicePort.addFunds(blockchainDto);
    }


    @PostMapping("/bank/withdraw")
    public TrxBlockchainDto withdrawMoney(@RequestBody BlockchainDto blockchainDto) {
        return blockchainServicePort.withdrawFunds(blockchainDto);
    }

    @PostMapping("/bank/withdraw-all")
    public TrxBlockchainDto withdrawAllMoney(@RequestBody BlockchainDto blockchainDto) {
        return blockchainServicePort.withdrawAllFunds(blockchainDto);
    }

    @PostMapping("/bank/transfer")
    public TrxBlockchainDto transferMoneyGanache(@RequestBody BankTransferDto blockchainDto) {
        return blockchainServicePort.transferTo(blockchainDto);
    }
}
