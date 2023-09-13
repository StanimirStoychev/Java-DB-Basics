package com.example.accountSystem;

import com.example.accountSystem.entities.Account;
import com.example.accountSystem.entities.User;
import com.example.accountSystem.services.AccountService;
import com.example.accountSystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final UserService userService;
    private final AccountService accountService;

    @Autowired
    public ConsoleRunner(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }


    @Override
    public void run(String... args) throws Exception {


        Account account = new Account();
        account.setBalance(new BigDecimal("25000"));

        User user = new User("Gosho", 20);
        user.setAccounts(new HashSet<>() {{
            add(account);
        }});

        userService.registerUser(user);

        accountService.depositMoney(new BigDecimal("30000"), account.getId());
        accountService.withdrawMoney(new BigDecimal("20000"), account.getId());
    }
}
