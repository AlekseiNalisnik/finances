package com.application.finances.controller;

import com.application.finances.entity.Wallet;
import com.application.finances.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WalletController {
    @Autowired
    private WalletService walletService;

    @GetMapping("/wallets")
    public List<Wallet> findAllWallets() {
        return walletService.findAllWallets();
    }

    @GetMapping("/wallet/{id}")
    public Wallet findWallet(@PathVariable Long id) {
        return walletService.findWalletById(id);
    }

    @GetMapping("/wallet-delete/{id}")
    public void deleteWallet(@PathVariable Long id) {
        walletService.deleteWallet(id);
    }

    @PostMapping("/wallet-create")
    public void updateWallet(Wallet wallet) {
        walletService.createWallet(wallet);
    }
}