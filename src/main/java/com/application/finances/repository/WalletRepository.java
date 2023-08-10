package com.application.finances.repository;

import com.application.finances.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    @Query("""
        select w from Wallet w
        join w.users u
        where u.username = :username
    """)
    List<Wallet> findAllByUsername(String username);

}
