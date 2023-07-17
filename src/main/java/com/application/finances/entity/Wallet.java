package com.application.finances.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "wallets")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 100, nullable = false, unique = true)
    private String name;

    @Column(name = "description", length = 250, nullable = false)
    private String description;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    @Column(name = "dateCreated", nullable = false)
    private Date dateCreated;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "users_wallets", joinColumns = {@JoinColumn(name = "wallet_id")}, inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private Set<User> users = new HashSet<User>();

    @OneToMany
    private List<Transaction> transactions;

//    public void removeUser(User user) {
//        this.users.remove(user);
//        user.getWallets().remove(user);
//    }
//
//    public void addUser(User user) {
//        this.users.add(user);
//        user.getWallets().add(this);
//    }
//
//    public void removeTransaction(Transaction transaction) {
//        this.transactions.remove(transaction);
//        transaction.getWallet().removeTransaction(transaction);
//    }
//
//    public void addTransaction(Transaction transaction) {
//        this.transactions.add(transaction);
//        transaction.getWallet().addTransaction(transaction);
//    }
}
