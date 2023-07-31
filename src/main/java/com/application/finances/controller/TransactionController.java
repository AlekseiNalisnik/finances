package com.application.finances.controller;

import com.application.finances.entity.Transaction;
import com.application.finances.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/finances/secured")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/transactions")
    public List<Transaction> getTransactions() {
        return transactionService.findAllTransactions();
    }

    @GetMapping("/transaction/{id}")
    public Transaction getTransaction(@PathVariable Long id) {
        return transactionService.findTransactionById(id);
    }

    @GetMapping("/transaction-delete/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
    }

    @PostMapping("/transaction-create")
    public void createTransaction(Transaction transaction) {
        transactionService.createTransaction(transaction);
    }
}
