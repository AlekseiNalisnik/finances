package com.application.finances.service;

import com.application.finances.dto.TransactionDto;
import com.application.finances.entity.Transaction;
import com.application.finances.entity.Wallet;
import com.application.finances.repository.TransactionRepository;
import com.application.finances.repository.WalletRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private DictionaryService dictionaryService;

    public List<Transaction> findAllTransactionsInWallet(Long walletId) {
        return transactionRepository.findByWalletId(walletId);
    }

    public Transaction createTransaction(Long walletId, TransactionDto transactionDto) {
        var wallet = walletRepository.findById(walletId)
            .orElseThrow(() -> new EntityNotFoundException("Кошелёк не найден"));
        var purpose = dictionaryService.findTransactionPurposeDictionaryById(transactionDto.getPurposeId())
            .orElseThrow(() -> new EntityNotFoundException("Назначение транзакции не найдено"));

        var transaction = new Transaction();
        transaction.setPrice(transactionDto.getPrice());
        transaction.setPaymentType(transactionDto.getPaymentType());
        transaction.setDateCreated(transactionDto.getDateCreated());
        transaction.setDescription(transactionDto.getDescription());
        transaction.setPurchasePlace(transactionDto.getPurchasePlace());
        transaction.setPurpose(purpose);
        transaction.addWallet(wallet);

        return transactionRepository.save(transaction);
    }

    public void updateTransaction(Transaction transaction) {
        Transaction transactionForUpdate = transactionRepository.findById(transaction.getId())
            .orElseThrow(() -> new EntityNotFoundException("Транзакция не найдена"));

        transactionForUpdate.setId(transaction.getId());
        transactionForUpdate.setDateCreated(transaction.getDateCreated());
        transactionForUpdate.setPrice(transaction.getPrice());
        transactionForUpdate.setPurchasePlace(transaction.getPurchasePlace());
        transactionForUpdate.setPurpose(transaction.getPurpose());
        transactionForUpdate.setPaymentType(transaction.getPaymentType());
        transactionForUpdate.setDescription(transaction.getDescription());

        transactionRepository.save(transactionForUpdate);
    }

    public void deleteTransaction(Long walletId, Long id) {
        Wallet wallet = walletRepository.findById(walletId)
            .orElseThrow(() -> new EntityNotFoundException("Кошелёк не найден"));
        Transaction transaction = transactionRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Транзакция не найдена"));

        transaction.deleteWallet(wallet);

        transactionRepository.deleteById(transaction.getId());
    }
}
