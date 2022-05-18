package com.sg.guessthenumber.service;

import com.sg.guessthenumber.data.GameDao;
import com.sg.guessthenumber.models.Game;

import java.util.ArrayList;
import java.util.List;

public class GameDaoDBTestStub implements GameDao {
    public Game onlyGame;

    public GameDaoDBTestStub() {
        onlyGame = new Game();
        onlyGame.setId(1);
        onlyGame.setAnswer(1234);
        onlyGame.setFinished(false);
    }

    @Override
    public List<Game> getAllGames() {
        List<Game> games = new ArrayList<>();
        games.add(onlyGame);
        return games;
    }

    @Override
    public Game getGameById(int id) {
        if (id == onlyGame.getId()) {
            return onlyGame;
        }
        return null;
    }

    @Override
    public Game createGame(Game game) {
        return game;
    }

    @Override
    public boolean updateGame(Game game) {
        return false;
    }

    @Override
    public boolean deleteGameById(int id) {
        return false;
    }
}
