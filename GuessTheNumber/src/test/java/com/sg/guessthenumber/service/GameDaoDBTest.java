package com.sg.guessthenumber.service;

import com.sg.guessthenumber.data.GameDao;
import com.sg.guessthenumber.models.Game;

import java.util.List;

public class GameDaoDBTest implements GameDao {
    @Override
    public List<Game> getAllGames() {
        return null;
    }

    @Override
    public Game getGameById(int id) {
        return null;
    }

    @Override
    public Game createGame(Game game) {
        return null;
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
