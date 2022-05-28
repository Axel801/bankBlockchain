package com.example.bank.bank.infraestructure.repository;

import java.util.List;

import com.example.bank.bank.domain.entity.Transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(value = "SELECT * FROM transaction WHERE from_id=?1 OR to_id=?1", nativeQuery = true)
    List<Transaction> findAllTransactionsById(Long idUser);

}
