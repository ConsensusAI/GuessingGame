package com.sg.guessthenumber.service;

import com.sg.guessthenumber.data.GameDao;
import com.sg.guessthenumber.models.Game;

import java.util.ArrayList;
import java.util.List;

public class GameDaoDBTestStub implements GameDao {
    public Game trueGame;
    public Game falseGame;

    public GameDaoDBTestStub() {
        trueGame = new Game();
        trueGame.setId(1);
        trueGame.setAnswer(1234);
        trueGame.setFinished(true);

        falseGame = new Game();
        falseGame.setId(2);
        falseGame.setAnswer(6789);
        falseGame.setFinished(false);
    }

    @Override
    public List<Game> getAllGames() {
        List<Game> games = new ArrayList<>();
        games.add(trueGame);
        return games;
    }

    @Override
    public Game getGameById(int id) {
        if (id == trueGame.getId()) {
            return trueGame;
        }
        if (id == falseGame.getId()) {
            return falseGame;
        }
        return null;
    }

    @Override
    public Game createGame(Game game) {
        return trueGame;
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
