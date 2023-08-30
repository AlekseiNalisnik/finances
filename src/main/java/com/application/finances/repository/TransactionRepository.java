package com.application.finances.repository;

import com.application.finances.entity.Transaction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>  {
    List<Transaction> findByWalletIdOrderByDateCreatedDesc(Long walletId, Pageable pageable);
}
