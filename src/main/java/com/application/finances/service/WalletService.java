package com.application.finances.service;

import com.application.finances.entity.Wallet;
import com.application.finances.repository.UserRepository;
import com.application.finances.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class WalletService {
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Wallet> findAllWallets() {
        return walletRepository.findAll();
    }

    public List<Wallet> findAllUserWallets(Principal principal) {
        return walletRepository.findAllByUsername(principal.getName());
    }

    public Wallet findWalletById(Long id) {
        return walletRepository.findById(id).orElseThrow(() -> new RuntimeException("Wallet not found"));
    }

    public Wallet createWallet(Wallet wallet, Principal principal) {
        var user = userRepository.findByUsername(principal.getName())
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        wallet.addUser(user);

        return walletRepository.save(wallet);
    }

    public void updateWallet(Wallet wallet) {
        Wallet walletForUpdate = walletRepository.findById(wallet.getId())
            .orElseThrow(() -> new RuntimeException("wallet not found"));

        walletForUpdate.setDescription(wallet.getDescription());
        walletForUpdate.setName(wallet.getName());
        walletForUpdate.setBalance(wallet.getBalance());
        walletForUpdate.setDateCreated(wallet.getDateCreated());
        walletForUpdate.setId(wallet.getId());

        walletRepository.save(walletForUpdate);
    }

    public void deleteWallet(Long id, Principal principal) {
        var user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Wallet wallet = walletRepository.findById(id).orElseThrow(() -> new RuntimeException("Wallet not found"));
        wallet.deleteUser(user);
        walletRepository.deleteById(wallet.getId());
    }
}
