package com.sg.guessthenumber.controllers;

import com.sg.guessthenumber.data.GameDao;
import com.sg.guessthenumber.models.Game;
import com.sg.guessthenumber.models.Round;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {

    GameDao dao;

    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public int begin() {
        return 0;
    }

    @PostMapping("/guess")
    public Round guess() {
        return null;
    }

    @GetMapping("/game")
    public List<Game> getAllGames() {
        return null;
    }

    @GetMapping("/game/{gameId}")
    public Game getGameById() {
        return null;
    }

    @GetMapping("/rounds/{gameId}")
    public List<Round> getRoundsForGame() {
        return null;
    }
}

/*
begin
guess
game
game/{gameId}
rounds/{gameId}
 */