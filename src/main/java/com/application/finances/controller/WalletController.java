package com.application.finances.controller;

import com.application.finances.entity.Wallet;
import com.application.finances.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/finances/secured")
public class WalletController {
    @Autowired
    private WalletService walletService;

    @GetMapping("/admin/wallets")
    public List<Wallet> findAllWallets() {
        return walletService.findAllWallets();
    }

    @GetMapping("/wallets")
    public ResponseEntity<List<Wallet>> findAllUserWallets(Principal principal) {
        return ResponseEntity.ok(walletService.findAllUserWallets(principal));
    }

    @GetMapping("/wallets/{id}")
    public Wallet findWallet(@PathVariable Long id) {
        return walletService.findWalletById(id);
    }

    @GetMapping("/wallets/delete/{id}")
    public ResponseEntity<HttpStatus> deleteWallet(@PathVariable Long id, Principal principal) {
        walletService.deleteWallet(id, principal);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/wallets/create")
    public ResponseEntity<Wallet> createWallet(@RequestBody Wallet wallet, Principal principal) {
        return ResponseEntity.ok(walletService.createWallet(wallet, principal));
    }

    @PutMapping("/wallets/update")
    public ResponseEntity<HttpStatus> updateWallet(@RequestBody Wallet wallet) {
        walletService.updateWallet(wallet);

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
