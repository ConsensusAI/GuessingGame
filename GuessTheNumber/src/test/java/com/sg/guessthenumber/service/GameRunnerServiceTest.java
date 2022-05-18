package com.sg.guessthenumber.service;

import com.sg.guessthenumber.data.GameDao;
import com.sg.guessthenumber.data.RoundDao;
import com.sg.guessthenumber.models.Round;
import junit.framework.TestCase;

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
    }

    public void testGetAllGames() {
    }

    public void testGetRoundsForGame() {
    }

    public void testGetGameById() {
    }
}