package com.example.bank.bank.infraestructure.adapters;

import com.example.bank.bank.domain.entity.Transaction;
import com.example.bank.bank.domain.entity.User;
import com.example.bank.bank.infraestructure.repository.TransactionRepository;
import com.example.bank.bank.infraestructure.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TransactionJpaAdapterTest {


    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    UserRepository userRepository;


    @Autowired
    private TestEntityManager entityManager;

    private Transaction transactionTest;

    private User userFromTest;

    private User userToTest;

    @BeforeEach
    public void setup() {

        this.userFromTest = User.builder()
                .name("fromTest")
                .amount(0)
                .build();
        userRepository.save(userFromTest);

        this.userToTest = User.builder()
                .name("toTest")
                .amount(0)
                .build();
        userRepository.save(userToTest);

        transactionTest = Transaction.builder()
                .from(userFromTest)
                .to(userToTest)
                .amount(10L)
                .build();
    }

    @Test
    void getTransactionsByUserId() {
        Transaction transaction1 = Transaction.builder()
                .from(this.userFromTest)
                .to(this.userToTest)
                .amount(10L)
                .build();

        Transaction transaction2 = Transaction.builder()
                .from(this.userFromTest)
                .to(this.userToTest)
                .amount(3L)
                .build();
        User newUser =  userRepository.save(new User(5L, "toTest5",  0));
        Transaction transaction3 = Transaction.builder()
                .from(userToTest)
                .to(newUser)
                .amount(3L)
                .build();

        transactionRepository.save(transaction1);
        transactionRepository.save(transaction2);
        transactionRepository.save(transaction3);

        List<Transaction> listTransaction = transactionRepository.findAllTransactionsById(this.userFromTest.getId());

        assertThat(listTransaction).isNotNull();
        assertThat(listTransaction.size()).isGreaterThanOrEqualTo(2);

    }


    @Test
    void addTransaction() {

        Transaction transactionSaved = transactionRepository.save(this.transactionTest);
        assertThat(transactionSaved).isNotNull();
        assertThat(transactionSaved.getId()).isGreaterThan(0);

    }
}