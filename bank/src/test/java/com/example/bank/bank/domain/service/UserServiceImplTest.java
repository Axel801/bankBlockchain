package com.example.bank.bank.domain.service;

import com.example.bank.bank.domain.data.AmountDto;
import com.example.bank.bank.domain.data.TransactionDto;
import com.example.bank.bank.domain.data.TransactionEntityDto;
import com.example.bank.bank.domain.data.UserDto;
import com.example.bank.bank.domain.port.api.UserServicePort;
import com.example.bank.bank.domain.port.spi.TransactionPersistencePort;
import com.example.bank.bank.domain.port.spi.UserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserPersistencePort userPersistencePort;

    @Mock
    private TransactionPersistencePort transactionPersistencePort;

    private UserServicePort userServicePort;

    private UserDto userDto;

    @BeforeEach
    void initUseCase() {

        this.userDto = new UserDto(1L, "TEST", 0);
        this.userServicePort = new UserServiceImpl(userPersistencePort, transactionPersistencePort);
    }

    @Test
    void addUser() throws IOException {

        UserDto userDto = new UserDto();
        userDto.setName("Test");

        when(userPersistencePort.addUser(any(UserDto.class))).thenReturn(this.userDto);

        UserDto savedUser = userServicePort.addUser(userDto);

        assertThat(savedUser.getName()).isNotNull();

    }

    // @Test
    // void deleteUser() {
    //
    // Mockito.doNothing().when(userPersistencePort).deleteUser(any(Long.class));
    // userServicePort.deleteUser(1L);
    // }

    @Test
    void updateUser() {
        UserDto userUpdate = new UserDto(1L, "TEST UPDATE", 10);
        when(userPersistencePort.updateUser(any(UserDto.class))).thenReturn(userUpdate);

        UserDto userUpdated = userServicePort.updateUser(this.userDto);

        assertThat(userUpdated.getName()).isEqualTo(userUpdate.getName());
        assertThat(userUpdated.getId()).isEqualTo(userUpdate.getId());
        assertThat(userUpdated.getAmount()).isEqualTo(userUpdate.getAmount());

    }

    @Test
    void getUserById() {

        when(userPersistencePort.getUserById(any(Long.class))).thenReturn(this.userDto);

        UserDto userDto = userServicePort.getUserById(1L);

        assertThat(userDto.getId()).isEqualTo(this.userDto.getId());

    }


    @Test
    void addMoney() {
        UserDto userUpdate = new UserDto(1L, "TEST UPDATE", 10);

        when(userPersistencePort.updateUser(any(UserDto.class))).thenReturn(userUpdate);
        when(userPersistencePort.getUserById(any(Long.class))).thenReturn(this.userDto);

        UserDto userUpdated = userServicePort.addMoney(new AmountDto(1L, 10));

        assertThat(userUpdated.getAmount()).isEqualTo(userUpdate.getAmount());

    }

    @Test
    void addTransaction() {
        UserDto userDto1 = new UserDto(1L, "TEST", 10);
        UserDto userDto2 = new UserDto(2L, "TEST2",  0);

        when(userPersistencePort.getUserById(any(Long.class))).thenReturn(userDto1, userDto2);

        TransactionDto transactionDto = new TransactionDto(null, userDto1.getId(), userDto2.getId(), 10L);

        TransactionDto transactionDtoResponse = new TransactionDto("1", userDto1.getId(), userDto2.getId(), 10L);
        when(transactionPersistencePort.addTransaction(any(TransactionEntityDto.class)))
                .thenReturn(transactionDtoResponse);

        TransactionDto transactionDtoUpdated = userServicePort.addTransaction(transactionDto);

        assertThat(transactionDtoUpdated.getId()).isEqualTo(transactionDtoResponse.getId());
    }

    @Test
    void getTransactionsByUserId() {

        List<TransactionDto> listTransactionDto = new ArrayList<>();

        listTransactionDto.add(new TransactionDto("1", 1L, 2L, 10L));
        listTransactionDto.add(new TransactionDto("2", 2L, 1L, 2L));

        when(transactionPersistencePort.getTransactionsByUserId(any(Long.class))).thenReturn(listTransactionDto);

        List<TransactionDto> listTransactionDtoResponse = userServicePort.getTransactionsByUserId(1L);

        assertThat(listTransactionDtoResponse.size()).isEqualTo(2);

    }

//    @Test
//    void getBalanceOfById() {
//
//        when(ganacheServicePort.getBalanceOf("wallet")).thenReturn(BigInteger.valueOf(10L));
//        when(userPersistencePort.getUserById(any(Long.class))).thenReturn(this.userDto);
//
//        BigInteger balance = userServicePort.getBalanceOfById(1L);
//
//        assertThat(balance.intValue()).isEqualTo(10);
//
//    }
}