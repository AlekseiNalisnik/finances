package com.application.finances.service;

import com.application.finances.entity.Wallet;
import com.application.finances.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletService {
    @Autowired
    private WalletRepository walletRepository;

    public List<Wallet> findAllWallets() {
        return walletRepository.findAll();
    }

    public Wallet findWalletById(Long id) {
        return walletRepository.findById(id).orElseThrow(() -> new RuntimeException("Wallet not found"));
    }

    public void createWallet(Wallet wallet) {
        walletRepository.save(wallet);
    }

    public void deleteWallet(Long id) {
        Wallet wallet = walletRepository.findById(id).orElseThrow(() -> new RuntimeException("Wallet not found"));
        walletRepository.deleteById(wallet.getId());
    }
}
