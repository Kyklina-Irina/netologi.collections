package ru.netology.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Player;
import ru.netology.exception.NotRegisteredException;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    Game game;
    Player player1;
    Player player2;
    Player player3;

    @BeforeEach
    void setUp() {
        game = new Game();
        player1 = new Player(1, "Alice", 10);
        player2 = new Player(2, "Bob", 8);
        player3 = new Player(3, "Charlie", 10);
    }

    @Test
    void shouldRegisterPlayers() {
        game.register(player1);
        game.register(player2);

        // Проверка через приватное поле невозможна напрямую, но мы проверим через round
        assertEquals(1, game.round("Alice", "Bob"));
    }

    @Test
    void shouldReturn1WhenFirstPlayerStronger() {
        game.register(player1);
        game.register(player2);
        assertEquals(1, game.round("Alice", "Bob"));
    }

    @Test
    void shouldReturn2WhenSecondPlayerStronger() {
        game.register(player2);
        game.register(player1);
        assertEquals(2, game.round("Bob", "Alice"));
    }

    @Test
    void shouldReturn0OnDraw() {
        game.register(player1);
        game.register(player3);
        assertEquals(0, game.round("Alice", "Charlie"));
    }

    @Test
    void shouldThrowExceptionWhenFirstPlayerNotRegistered() {
        game.register(player2);
        assertThrows(NotRegisteredException.class, () -> {
            game.round("Alice", "Bob");
        });
    }

    @Test
    void shouldThrowExceptionWhenSecondPlayerNotRegistered() {
        game.register(player1);
        assertThrows(NotRegisteredException.class, () -> {
            game.round("Alice", "Charlie");
        });
    }

    @Test
    void shouldThrowExceptionWhenBothPlayersNotRegistered() {
        assertThrows(NotRegisteredException.class, () -> {
            game.round("Alice", "Charlie");
        });
    }
}