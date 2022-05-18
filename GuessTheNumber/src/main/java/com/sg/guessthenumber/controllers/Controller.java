package com.sg.guessthenumber.controllers;

import com.sg.guessthenumber.data.GameDao;
import com.sg.guessthenumber.data.RoundDao;
import com.sg.guessthenumber.models.Game;
import com.sg.guessthenumber.models.Round;
import com.sg.guessthenumber.service.GameRunnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api")
public class Controller {

    private final GameRunnerService gameRunner;

    public Controller(GameRunnerService gameRunner) {
        this.gameRunner = gameRunner;
    }

    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public int begin() {
        return gameRunner.createGame();
    }

    @PostMapping("/guess")
    public Round guess(@RequestBody Round round) {
        return gameRunner.checkGuess(round);
    }

    @GetMapping("/game")
    public List<Game> getAllGames() {
        return gameRunner.getAllGames();
    }

    @GetMapping("/game/{gameId}")
    public ResponseEntity<Game> getGameById(@PathVariable int gameId) {
        Game game = gameRunner.getGameById(gameId);
        if (game == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(game);
    }

    @GetMapping("/rounds/{gameId}")
    public List<Round> getRoundsForGame(@PathVariable int gameId) {
        return gameRunner.getRoundsForGame(gameId);
    }
}
