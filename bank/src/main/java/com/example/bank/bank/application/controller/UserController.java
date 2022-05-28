package com.example.bank.bank.application.controller;

import java.io.IOException;
import java.util.List;

import com.example.bank.bank.domain.data.AmountDto;
import com.example.bank.bank.domain.data.TransactionDto;
import com.example.bank.bank.domain.data.UserDto;
import com.example.bank.bank.domain.port.api.UserServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServicePort userServicePort;


    @GetMapping("/{id}")
    public UserDto getUserInfo(@PathVariable(name = "id") long id) {
        return userServicePort.getUserById(id);
    }


    //TODO change to Create
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto addUser(@RequestBody UserDto userDto) throws IOException {
        return this.userServicePort.addUser(userDto);
    }

    @PostMapping("/add-money")
    public UserDto addMoney(@RequestBody AmountDto amountDto) {
        return this.userServicePort.addMoney(amountDto);
    }

    @PostMapping("/transfer")
    public TransactionDto addTransaction(@RequestBody TransactionDto transactionDto) {
        return userServicePort.addTransaction(transactionDto);
    }

    @GetMapping("/transaction/{userId}")
    public List<TransactionDto> getAllTransactionByUserId(@PathVariable(name = "userId") long userId) {
        return userServicePort.getTransactionsByUserId(userId);
    }


}
