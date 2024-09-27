package com.sj.springboot.services;

import com.sj.springboot.models.Account;
import com.sj.springboot.repositories.AccountRepository;
import com.sj.springboot.services.impl.TransactionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    private Account sourceAccount;
    private Account destinationAccount;

    @BeforeEach
    void setUp() {
        // Setup source and destination accounts
        sourceAccount = new Account();
        sourceAccount.setAccountNumber("12345");
        sourceAccount.setBalance(1000.0);

        destinationAccount = new Account();
        destinationAccount.setAccountNumber("67890");
        destinationAccount.setBalance(500.0);
    }

    @Test
    void transferFunds_Success() {
        // Arrange
        String fromAccount = "12345";
        String toAccount = "67890";
        Double amount = 200.0;

        // Mocking the accountRepository to return the accounts
        when(accountRepository.findByAccountNumber(fromAccount)).thenReturn(sourceAccount);
        when(accountRepository.findByAccountNumber(toAccount)).thenReturn(destinationAccount);

        // Act
        transactionService.transferFunds(fromAccount, toAccount, amount);

        // Assert
        verify(accountRepository, times(1)).save(sourceAccount);
        verify(accountRepository, times(1)).save(destinationAccount);

        // Check that the balances are updated correctly
        assert sourceAccount.getBalance() == 800.0;
        assert destinationAccount.getBalance() == 700.0;
    }

    @Test
    void transferFunds_InvalidAccount() {
        // Arrange
        String fromAccount = "invalid";
        String toAccount = "67890";
        Double amount = 200.0;

        // Mock the repository to return null for invalid account number
        when(accountRepository.findByAccountNumber(fromAccount)).thenReturn(null);

        // Act & Assert: Expect IllegalArgumentException for invalid account
        assertThrows(IllegalArgumentException.class, () -> {
            transactionService.transferFunds(fromAccount, toAccount, amount);
        });

        // Verify no save operations were performed
        verify(accountRepository, never()).save(any(Account.class));
    }

    @Test
    void transferFunds_InsufficientBalance() {
        // Arrange
        String fromAccount = "12345";
        String toAccount = "67890";
        Double amount = 1500.0; // More than the available balance

        // Mock the account repository to return the valid accounts
        when(accountRepository.findByAccountNumber(fromAccount)).thenReturn(sourceAccount);
        when(accountRepository.findByAccountNumber(toAccount)).thenReturn(destinationAccount);

        // Act & Assert: Expect IllegalArgumentException for insufficient balance
        assertThrows(IllegalArgumentException.class, () -> {
            transactionService.transferFunds(fromAccount, toAccount, amount);
        });

        // Verify no save operations were performed
        verify(accountRepository, never()).save(any(Account.class));
    }
}
