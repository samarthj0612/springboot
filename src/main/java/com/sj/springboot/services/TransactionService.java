package com.sj.springboot.services;

public interface TransactionService {
    void transferFunds(String fromAccount, String toAccount, Double amount);
}
