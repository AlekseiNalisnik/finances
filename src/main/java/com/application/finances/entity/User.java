package com.application.finances.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 350, nullable = false, unique = true)
    private String name;

    @Column(name = "passhash", nullable = false, unique = true)
    private String passhash;

    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private Set<Wallet> wallets = new HashSet<Wallet>();
}
