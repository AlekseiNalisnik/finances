package com.application.finances.entity;

import com.application.finances.dictionaries.TransactionPurposeDictionary;
import com.application.finances.enums.TransactionPaymentTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "purchasePlace", length = 150, nullable = false)
    private String purchasePlace;

    @Column(name = "paymentType", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionPaymentTypeEnum paymentType;

    @Column(name = "dateCreated", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @ManyToOne
    private TransactionPurposeDictionary purpose;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "description", length = 250)
    private String description;

    @ManyToOne
    @JsonIgnore
    private Wallet wallet;

    public void addWallet(Wallet wallet) {
        this.wallet = wallet;
        wallet.getTransactions().add(this);
    }

    public void deleteWallet(Wallet wallet) {
        this.wallet = null;
        wallet.getTransactions().remove(this);
    }
}
