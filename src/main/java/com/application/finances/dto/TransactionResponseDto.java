package com.application.finances.dto;

import com.application.finances.entity.Transaction;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TransactionResponseDto {
    private List<Transaction> transactions;
    private long totalPages;
}
