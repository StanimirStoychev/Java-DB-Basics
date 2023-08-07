package com.example.accountsystem.services;

import com.example.accountsystem.models.Account;
import com.example.accountsystem.models.User;
import com.example.accountsystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(User user) {
        Optional<User> findUser = userRepository.findById(user.getId());

        if (findUser.isPresent()) {
            throw new IllegalArgumentException("This user exists!");
        }

        User userToSave = new User(user.getUsername(), user.getAge());

        this.userRepository.saveAndFlush(userToSave);
    }
}
