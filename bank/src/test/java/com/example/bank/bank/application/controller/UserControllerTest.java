package com.example.bank.bank.application.controller;

import com.example.bank.bank.domain.data.AmountDto;
import com.example.bank.bank.domain.data.TransactionDto;
import com.example.bank.bank.domain.data.UserDto;
import com.example.bank.bank.domain.entity.User;
import com.example.bank.bank.domain.port.api.UserServicePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServicePort userService;


    private ObjectMapper objectMapper;


    @BeforeEach
    void setup() {
        this.objectMapper = new ObjectMapper();
    }

    @Test
    void testGetUserInfo() throws Exception {
        UserDto userDto = new UserDto(1L, "TEST", 0);

        when(userService.getUserById(any(Long.class))).thenReturn(userDto);

        ResultActions response = mockMvc.perform(get("/user/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(userDto.getId()))
                .andExpect(jsonPath("$.name").value(userDto.getName()))
                .andExpect(jsonPath("$.amount").value(userDto.getAmount()));

        verify(userService).getUserById(1L);
    }

    @Test
    void testAddUser() throws Exception {
        User user = User.builder().name("Test").build();
        UserDto userDto = new UserDto();
        userDto.setName("Test");

        when(userService.addUser(any(UserDto.class))).thenReturn(userDto);

        ResultActions response = mockMvc.perform(post("/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)));

        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(userDto.getName()));

        verify(userService).addUser(userDto);
    }

    @Test
    void testAddMoney() throws Exception {
        AmountDto amountDto = new AmountDto(1L, 99);
        UserDto userDto = new UserDto(1L, "TEST", 99);


        when(userService.addMoney(any(AmountDto.class))).thenReturn(userDto);

        ResultActions response = mockMvc.perform(post("/user/add-money")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(amountDto)));

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(userDto.getId()))
                .andExpect(jsonPath("$.name").value(userDto.getName()))
                .andExpect(jsonPath("$.amount").value(userDto.getAmount()));

        verify(userService).addMoney(amountDto);
    }

    @Test
    void testAddTransaction() throws Exception {

        TransactionDto sendTransactionDto = new TransactionDto();
        sendTransactionDto.setFromId(1L);
        sendTransactionDto.setToId(2L);
        sendTransactionDto.setValue(10L);

        TransactionDto responseTransactionDto = new TransactionDto("1", 1L, 2L, 10L);


        when(userService.addTransaction(any(TransactionDto.class))).thenReturn(responseTransactionDto);

        ResultActions response = mockMvc.perform(post("/user/transfer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sendTransactionDto)));

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(responseTransactionDto.getId()))
                .andExpect(jsonPath("$.fromId").value(responseTransactionDto.getFromId()))
                .andExpect(jsonPath("$.toId").value(responseTransactionDto.getToId()))
                .andExpect(jsonPath("$.value").value(responseTransactionDto.getValue()));


    }

    @Test
    void testGetAllTransactionByUserId() throws Exception {

        List<TransactionDto> listTransactions = new ArrayList<>();
        listTransactions.add(new TransactionDto("1", 1L, 2L, 10L));
        listTransactions.add(new TransactionDto("2", 3L, 1L, 1L));

        when(userService.getTransactionsByUserId(any(Long.class))).thenReturn(listTransactions);


        ResultActions response = mockMvc.perform(get("/user/transaction/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(listTransactions.get(0).getId()))
                .andExpect(jsonPath("$[0].fromId").value(listTransactions.get(0).getFromId()))
                .andExpect(jsonPath("$[0].toId").value(listTransactions.get(0).getToId()))
                .andExpect(jsonPath("$[0].value").value(listTransactions.get(0).getValue()))
                .andExpect(jsonPath("$[1].id").value(listTransactions.get(1).getId()))
                .andExpect(jsonPath("$[1].fromId").value(listTransactions.get(1).getFromId()))
                .andExpect(jsonPath("$[1].toId").value(listTransactions.get(1).getToId()))
                .andExpect(jsonPath("$[1].value").value(listTransactions.get(1).getValue()));

        verify(userService).getTransactionsByUserId(1L);
    }

//    @Test
//    void testGetTotalBalanceGanache() throws Exception {
//
//        //Mock response
//        BigInteger bigInteger = BigInteger.valueOf(10L);
//
//        //Mock service
//        when(ganacheService.getTotalBalances()).thenReturn(BigInteger.valueOf(10L));
//
//
//        //Response
//        ResultActions response = mockMvc.perform(get("/user/ganache")
//                .contentType(MediaType.APPLICATION_JSON));
//
//        response.andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$").value(bigInteger.intValue()));
//
//        verify(ganacheService).getTotalBalances();
//    }

//    @Test
//    void testGetTotalBalanceOfGanache() throws Exception {
//        //Mock response
//        BigInteger bigInteger = BigInteger.valueOf(10L);
//
//        //Mock service
//        when(userService.getBalanceOfById(any(Long.class))).thenReturn(BigInteger.valueOf(10L));
//
//
//        //Response
//        ResultActions response = mockMvc.perform(get("/user/ganache/1")
//                .contentType(MediaType.APPLICATION_JSON));
//
//        response.andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$").value(bigInteger.intValue()));
//
//        verify(userService).getBalanceOfById(1L);
//    }
}