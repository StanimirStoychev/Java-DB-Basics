package com.example.accountsystem.services;

import com.example.accountsystem.models.Account;
import com.example.accountsystem.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void withdrawMoney(BigDecimal money, Long id) {
        Optional<Account> account = accountRepository.findById(id);

        if (account.isPresent()){
            throw new NoSuchElementException("Account already exist!");
        }

        if (money.compareTo(account.get().getBalance()) > 0) {
            throw new IllegalArgumentException("Don't have enough balance!");
        }

        BigDecimal current = account.get().getBalance();

        account.get().setBalance(current.subtract(money));

        this.accountRepository.save(account.get());
    }

    @Override
    public void depositMoney(BigDecimal money, Long id) {
        Account account = accountRepository.findById(id).orElseThrow(NoSuchElementException::new);

        if (money.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Value can't be negative!");
        }

        BigDecimal balance = account.getBalance().add(money);
        account.setBalance(balance);

        this.accountRepository.save(account);
    }
}
