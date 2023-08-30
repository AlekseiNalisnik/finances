package com.application.finances.controller;

import com.application.finances.dto.TransactionRequestDto;
import com.application.finances.entity.Transaction;
import com.application.finances.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/finances/secured/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/{walletId}/list")
    public ResponseEntity<List<Transaction>> getAllTransactionsInWallet(@PathVariable Long walletId) {
        return ResponseEntity.ok(transactionService.findAllTransactionsInWallet(walletId));
    }

    @DeleteMapping("/{walletId}/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTransaction(@PathVariable Long walletId, @PathVariable Long id) {
        transactionService.deleteTransaction(walletId, id);
    }

    @PostMapping("/{walletId}/create")
    public ResponseEntity<Transaction> createTransaction(
            @PathVariable Long walletId,
            @RequestBody TransactionRequestDto transactionRequestDto) {
        return ResponseEntity.ok(transactionService.createTransaction(walletId, transactionRequestDto));
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void updateTransaction(@RequestBody Transaction transaction) {
        transactionService.updateTransaction(transaction);
    }
}
