package com.sg.guessthenumber.data;

import com.sg.guessthenumber.App;
import com.sg.guessthenumber.models.Game;
import com.sg.guessthenumber.models.Round;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class GameDatabaseDaoTest {

    @Autowired
    GameDao gameDao;

    @Autowired
    RoundDao roundDao;

    @Before
    public void setUp() {
        List<Round> rounds = roundDao.getAllRounds();
        for (Round round : rounds) {
            roundDao.deleteRoundById(round.getId());
        }

        List<Game> games = gameDao.getAllGames();
        for (Game game : games) {
            gameDao.deleteGameById(game.getId());
        }
    }

    @Test
    public void testGetAllGames() {
        Game game = new Game();
        game.setAnswer(1234);
        game = gameDao.createGame(game);

        Game game2 = new Game();
        game.setAnswer(6789);
        game = gameDao.createGame(game);

        List<Game> games = gameDao.getAllGames();

        assertEquals(2, games.size());
        assertTrue(games.contains(game));
        assertTrue(games.contains(game2));
    }

    @Test
    public void testCreateGetGame() {
        Game game = new Game();
        game.setAnswer(1234);
        game = gameDao.createGame(game);

        Game fromDao = gameDao.getGameById(game.getId());

        assertEquals(game, fromDao);
    }

    @Test
    public void testUpdateGame() {
    }

    @Test
    public void testDeleteGameById() {
    }
}