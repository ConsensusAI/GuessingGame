package com.sg.guessthenumber.data;

import com.sg.guessthenumber.App;
import com.sg.guessthenumber.models.Game;
import com.sg.guessthenumber.models.Round;
import junit.framework.TestCase;
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
public class RoundDatabaseDaoTest extends TestCase {

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
    public void testGetAllRounds() {
        Game game = new Game();
        game.setAnswer(1234);
        game = gameDao.createGame(game);

        Round round = new Round();
        round.setGameId(game.getId());
        round.setGuess(1435);
        round.setResult("e:p:e:0");
        round = roundDao.addRound(round);

        Round round2 = new Round();
        round2.setGameId(game.getId());
        round2.setGuess(1937);
        round2.setResult("e:p:e:0");
        round2 = roundDao.addRound(round2);

        List<Round> rounds = roundDao.getAllRounds();

        assertEquals(2, rounds.size());
        assertTrue(rounds.contains(round));
        assertTrue(rounds.contains(round2));
    }

    @Test
    public void testAddGetRound() {
        Game game = new Game();
        game.setAnswer(1234);
        game = gameDao.createGame(game);

        Round round = new Round();
        round.setGameId(game.getId());
        round.setGuess(1435);
        round.setResult("e:p:e:0");
        round = roundDao.addRound(round);

        Round fromDao = roundDao.getRoundById(round.getId());

        assertEquals(round, fromDao);
    }

    @Test
    public void testUpdateRound() {
    }

    @Test
    public void testDeleteRoundById() {
    }

    @Test
    public void testGetRoundsForGame() {
    }
}