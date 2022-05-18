package com.sg.guessthenumber.service;

import com.sg.guessthenumber.data.GameDao;
import com.sg.guessthenumber.data.RoundDao;
import com.sg.guessthenumber.models.Game;
import com.sg.guessthenumber.models.Round;
import junit.framework.TestCase;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class GameRunnerServiceTest extends TestCase {

    private GameRunnerService gameRunner;

    private GameDao gameDao;
    private RoundDao roundDao;
    private final String PERFECT_MATCH = "e:e:e:e";

    public GameRunnerServiceTest() {
        this.gameDao = new GameDaoDBTestStub();
        this.roundDao = new RoundDaoDBTestStub();
        gameRunner = new GameRunnerService(gameDao, roundDao);
    }

    public void testCheckGuess() {
        Round round = roundDao.getRoundById(1);
        round = gameRunner.checkGuess(round);

        assertEquals(round.getResult(), "0:e:p:0");
    }

    public void testCreateGame() {
        int newGameId = gameRunner.createGame();

        Game game = gameRunner.getGameById(newGameId);
        int gameAnswer = game.getAnswer();
        int answerLength = 0;
        while (gameAnswer > 0) {
            gameAnswer /= 10;
            answerLength++;
        }
        assertEquals(4, answerLength);
        assertEquals(1, newGameId);
    }

    public void testGetAllGames() {
    }

    public void testGetGameById() {
        Game game = gameRunner.getGameById(1);
        Game game2 = gameRunner.getGameById(2);

        assertEquals(1234, game.getAnswer());
        assertEquals(0, game2.getAnswer());
    }
}