package com.example;

import com.example.entities.Account;
import com.example.entities.User;
import com.example.services.AccountService;
import com.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
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

        User user = new User("Goshox        ", 20);

        Account account = new Account();
        account.setBalance(new BigDecimal("25000"));

        user.setAccounts(new HashSet<>() {{
            add(account);
        }});

        userService.registerUser(user);

        accountService.depositMoney(new BigDecimal("30000"), account.getId());
        accountService.withdrawMoney(new BigDecimal("20000"), account.getId());
    }
}
