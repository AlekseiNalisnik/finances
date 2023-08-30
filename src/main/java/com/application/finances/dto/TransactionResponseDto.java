package com.application.finances.dto;

import lombok.Data;

import java.util.List;

@Data
public class TransactionResponseDto {
    private List<TransactionsWithDateDto> transactionsWithDate;
    private long totalPages;
}
