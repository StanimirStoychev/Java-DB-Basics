package com.example.accountsystem;

import com.example.accountsystem.models.Account;
import com.example.accountsystem.models.User;
import com.example.accountsystem.services.AccountService;
import com.example.accountsystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
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
    @Transactional
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
