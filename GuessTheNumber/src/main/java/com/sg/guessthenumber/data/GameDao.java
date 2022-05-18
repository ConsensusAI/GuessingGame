package com.sg.guessthenumber.data;

import com.sg.guessthenumber.models.Game;

import java.util.List;

public interface GameDao {
    List<Game> getAllGames();
    Game getGameById(int id);
    Game createGame(Game game);
    boolean updateGame(Game game);
    boolean deleteGameById(int id);

}
