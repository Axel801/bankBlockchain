package com.example.bank.bank.infraestructure.adapters;

import com.example.bank.bank.domain.entity.User;
import com.example.bank.bank.infraestructure.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserJpaAdapterTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    private User userTest;

    @BeforeEach
    public void setup() {
        userTest = User.builder()
                .name("Test")
                .amount(0)
                .build();
    }

    @Test
    void addUser() throws Exception {


        User userSaved = userRepository.save(this.userTest);
        assertThat(userSaved).isNotNull();
        assertThat(userSaved.getId()).isGreaterThan(0);
    }

    @Test
    void deleteUser() {

        userRepository.save(this.userTest);
        userRepository.deleteById(this.userTest.getId());

        Optional<User> userOptional = userRepository.findById(this.userTest.getId());
        assertThat(userOptional).isEmpty();


    }

    @Test
    void updateUser() {

        userRepository.save(this.userTest);
        User savedUser = userRepository.findById(this.userTest.getId()).get();
        savedUser.setName("nuevoNombre");

        User userUpdated = userRepository.save(savedUser);
        assertThat(userUpdated.getName()).isEqualTo("nuevoNombre");
    }

    @Test
    void getLastIndex() {
        userRepository.save(this.userTest);

        Long lastIndex = userRepository.findTopByOrderByIdDesc().getId();
        assertThat(this.userTest.getId()).isEqualTo(lastIndex);
    }

    @Test
    void getUserById() {

        userRepository.save(this.userTest);

        User userDb = userRepository.findById(this.userTest.getId()).get();

        assertThat(userDb).isNotNull();
    }
}