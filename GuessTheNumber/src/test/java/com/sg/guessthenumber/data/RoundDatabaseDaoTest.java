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

import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;

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
        round2.setResult("e:0:e:0");
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
        Game game = new Game();
        game.setAnswer(1234);
        game = gameDao.createGame(game);

        Round round = new Round();
        round.setGameId(game.getId());
        round.setGuess(1435);
        round.setResult("e:p:e:0");
        round = roundDao.addRound(round);

        Round fromDao = roundDao.getRoundById(round.getId());

        round.setGuess(1937);
        round.setResult("e:0:e:0");

        roundDao.updateRound(round);

        assertNotEquals(round, fromDao);

        fromDao = roundDao.getRoundById(round.getId());

        assertEquals(round, fromDao);
    }

    @Test
    public void testDeleteRoundById() {
        Game game = new Game();
        game.setAnswer(1234);
        game = gameDao.createGame(game);

        Round round = new Round();
        round.setGameId(game.getId());
        round.setGuess(1435);
        round.setResult("e:p:e:0");
        round = roundDao.addRound(round);

        roundDao.deleteRoundById(round.getId());

        Round fromDao = roundDao.getRoundById(round.getId());
        assertNull(fromDao);
    }

    @Test
    public void testGetRoundsForGame() {
        Game game = new Game();
        game.setAnswer(1234);
        game = gameDao.createGame(game);

        Game game2 = new Game();
        game2.setAnswer(6789);
        game2 = gameDao.createGame(game2);

        Round round = new Round();
        round.setGameId(game.getId());
        round.setGuess(1435);
        round.setResult("e:p:e:0");
        round = roundDao.addRound(round);

        Round round2 = new Round();
        round2.setGameId(game2.getId());
        round2.setGuess(8731);
        round2.setResult("p:e:0:0");
        round2 = roundDao.addRound(round2);

        Round round3 = new Round();
        round3.setGameId(game.getId());
        round3.setGuess(1937);
        round3.setResult("e:0:e:0");
        round3 = roundDao.addRound(round3);

        List<Round> roundsForGame = roundDao.getRoundsForGame(game.getId());

        assertEquals(2, roundsForGame.size());
        assertTrue(roundsForGame.contains(round));
        assertTrue(roundsForGame.contains(round3));
        assertFalse(roundsForGame.contains(round2));
    }
}