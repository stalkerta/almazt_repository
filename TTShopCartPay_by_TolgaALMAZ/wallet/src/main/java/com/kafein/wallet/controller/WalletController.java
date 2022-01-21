package com.kafein.wallet.controller;

import com.kafein.wallet.entity.Wallet;
import com.kafein.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    WalletService walletService;

    @GetMapping("/{userId}")
    @ResponseBody
    public ResponseEntity<List<Wallet>> getWallets(@PathVariable Long userId) {
        try {
            return new ResponseEntity<>(walletService.getWalletsByUserId(userId), HttpStatus.OK);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(e.getStatusCode());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Tolga ALMAZ: withdraw-money-from-wallet controller is introduced and processPayment service is called to withdraw money from wallet/wallets.
    @PutMapping("/withdraw-money-from-wallet/{userId}/{amount}")
    @ResponseBody
    public ResponseEntity<String> withdrawMoneyFromWallet(@PathVariable Long userId, @PathVariable Long amount) {
        try {
            return new ResponseEntity<>(walletService.processPayment(userId, amount), HttpStatus.OK);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(e.getStatusCode());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
