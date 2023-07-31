package com.application.finances.controller;

import com.application.finances.entity.Wallet;
import com.application.finances.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/finances")
public class WalletController {
    @Autowired
    private WalletService walletService;

    @GetMapping("/admin/wallets")
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
