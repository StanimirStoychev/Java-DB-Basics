package com.example.football.constants;

import java.nio.file.Path;

public enum Paths {
    ;

    public static final Path TOWNS_PATH = Path.of("src/main/resources/files/json/towns.json");
    public static final Path TEAMS_PATH = Path.of("src/main/resources/files/json/teams.json");

    public static final Path STATS_PATH = Path.of("src/main/resources/files/xml/stats.xml");
    public static final Path PLAYERS_PATH = Path.of("src/main/resources/files/xml/players.xml");
}
