package com.example.bank.bank.infraestructure.repository;

import com.example.bank.bank.domain.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findTopByOrderByIdDesc();
}
