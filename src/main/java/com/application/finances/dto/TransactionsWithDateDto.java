package com.application.finances.dto;

import com.application.finances.entity.Transaction;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
public class TransactionsWithDateDto {
    private Timestamp date;
    private List<Transaction> transactions;
}
