package com.application.finances.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50, nullable = false, unique = true)
    private String name;

    @Column(name = "type", length = 50, nullable = false)
    private String type;

    @Column(name = "dateCreated", nullable = false)
    private Date dateCreated;

    @Column(name = "category", length = 75, nullable = false)
    private String category;

    @Column(name = "money", nullable = false)
    private BigDecimal money;

    @ManyToOne
    private Wallet wallet;
}
