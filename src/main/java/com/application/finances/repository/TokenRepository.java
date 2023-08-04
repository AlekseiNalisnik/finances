package com.application.finances.repository;

import com.application.finances.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query("""
        select t from Token t
        inner join User u
        on t.user.id = u.id
        where u.id = :userId and (t.expired = false or t.revoked = false)
    """)
    Token findValidTokenByUser(Long userId);

    Optional<Token> findByToken(String token);
}
