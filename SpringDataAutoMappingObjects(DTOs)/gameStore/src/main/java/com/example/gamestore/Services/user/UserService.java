package com.example.gamestore.Services.user;

import com.example.gamestore.domain.entities.User;

public interface UserService {
    String registerUser(String[] args);

    String loginUser(String[] args);

    String logoutUser();

    User getLoggedInUser();
}
