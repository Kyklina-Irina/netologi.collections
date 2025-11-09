package ru.netology.service;

import ru.netology.domain.Player;
import ru.netology.exception.NotRegisteredException;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final List<Player> registeredPlayers = new ArrayList<>();

    public void register(Player player) {
        registeredPlayers.add(player);
    }

    private Player findByName(String name) {
        for (Player player : registeredPlayers) {
            if (player.getName().equals(name)) {
                return player;
            }
        }
        return null;
    }

    public int round(String playerName1, String playerName2) {
        Player player1 = findByName(playerName1);
        Player player2 = findByName(playerName2);

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