package com.sj.springboot.controllers;

import com.sj.springboot.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transfer")
    public String transferFunds(@RequestParam String fromAccount, @RequestParam String toAccount, @RequestParam Double amount) {
        try {
            transactionService.transferFunds(fromAccount, toAccount, amount);
            return "Transfer successful";
        } catch (Exception e) {
            return "Transfer failed: " + e.getMessage();
        }
    }
}
