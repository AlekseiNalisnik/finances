package com.application.finances.service;

import com.application.finances.dto.TransactionRequestDto;
import com.application.finances.dto.TransactionsWithDateDto;
import com.application.finances.entity.Transaction;
import com.application.finances.entity.Wallet;
import com.application.finances.repository.TransactionRepository;
import com.application.finances.repository.WalletRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private DictionaryService dictionaryService;

    public List<Transaction> findAllTransactionsInWallet(Long walletId) {
//        TODO: возможно создать новый метод в репозитории findByWalletIdOrderedByDateCreated
        List<Transaction> transactionsInWallet = transactionRepository.findByWalletId(walletId);
        var transactionsWithDate = transactionsInWallet.stream().map(this::reorganizeTransactionsByDate);

        return transactionRepository.findByWalletId(walletId);
    }

    public Transaction createTransaction(Long walletId, TransactionRequestDto transactionRequestDto) {
        var wallet = walletRepository.findById(walletId)
            .orElseThrow(() -> new EntityNotFoundException("Кошелёк не найден"));
        var purpose = dictionaryService.findTransactionPurposeDictionaryById(transactionRequestDto.getPurposeId())
            .orElseThrow(() -> new EntityNotFoundException("Назначение транзакции не найдено"));

        var transaction = new Transaction();
        transaction.setPrice(transactionRequestDto.getPrice());
        transaction.setPaymentType(transactionRequestDto.getPaymentType());
        transaction.setDateCreated(transactionRequestDto.getDateCreated());
        transaction.setDescription(transactionRequestDto.getDescription());
        transaction.setPurchasePlace(transactionRequestDto.getPurchasePlace());
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

    private TransactionsWithDateDto reorganizeTransactionsByDate(Transaction transaction) {
        return TransactionsWithDateDto.builder()

            .build();
    }
}
