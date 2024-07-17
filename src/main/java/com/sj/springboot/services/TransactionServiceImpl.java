package com.sj.springboot.services;

import com.sj.springboot.models.Account;
import com.sj.springboot.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void transferFunds(String fromAccount, String toAccount, Double amount) {
        Account source = accountRepository.findByAccountNumber(fromAccount);
        Account destination = accountRepository.findByAccountNumber(toAccount);

        if (source == null || destination == null) {
            throw new IllegalArgumentException("Invalid account number");
        }

        if (source.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient balance");
        }

        source.setBalance(source.getBalance() - amount);
        destination.setBalance(destination.getBalance() + amount);

        accountRepository.save(source);
        accountRepository.save(destination);
    }
}
