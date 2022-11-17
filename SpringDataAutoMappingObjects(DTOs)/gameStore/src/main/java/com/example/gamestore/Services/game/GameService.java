package com.example.gamestore.Services.game;

public interface GameService {

    String addGame(String[] args);

    String editGame(String[] args);

    String deleteGame(Long id);
}
