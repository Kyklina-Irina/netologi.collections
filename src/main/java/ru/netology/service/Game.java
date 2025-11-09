package ru.netology.service;

import ru.netology.domain.Player;
import ru.netology.exception.NotRegisteredException;

import java.util.HashMap;
import java.util.Map;

public class Game {
    private final Map<String, Player> registeredPlayers = new HashMap<>();

    public void register(Player player) {
        registeredPlayers.put(player.getName(), player);
    }

    public int round(String playerName1, String playerName2) {
        Player player1 = registeredPlayers.get(playerName1);
        Player player2 = registeredPlayers.get(playerName2);

        if (player1 == null) {
            throw new NotRegisteredException("Player not registered: " + playerName1);
        }
        if (player2 == null) {
            throw new NotRegisteredException("Player not registered: " + playerName2);
        }

        if (player1.getStrength() > player2.getStrength()) {
            return 1;
        } else if (player1.getStrength() < player2.getStrength()) {
            return 2;
        } else {
            return 0;
        }
    }
}