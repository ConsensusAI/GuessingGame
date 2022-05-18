package com.sg.guessthenumber.service;

import com.sg.guessthenumber.data.RoundDao;
import com.sg.guessthenumber.models.Game;
import com.sg.guessthenumber.models.Round;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RoundDaoDBTestStub implements RoundDao {
    public Round onlyRound;
    public Game onlyGame;

    public RoundDaoDBTestStub() {
        onlyGame = new Game();
        onlyGame.setId(1);
        onlyGame.setAnswer(1234);
        onlyGame.setFinished(false);

        onlyRound = new Round();
        onlyRound.setId(1);
        onlyRound.setGameId(1);
        onlyRound.setGuess(7245);
        onlyRound.setTime(Timestamp.valueOf(LocalDateTime.now()));
//        onlyRound.setResult("0:e:p:0");
        onlyRound.setResult("");
    }

    @Override
    public List<Round> getAllRounds() {
        List<Round> rounds = new ArrayList<>();
        rounds.add(onlyRound);
        return rounds;
    }

    @Override
    public Round getRoundById(int id) {
        if (id == onlyRound.getId()) {
            return onlyRound;
        }
        return null;
    }

    @Override
    public Round addRound(Round round) {
        return round;
    }

    @Override
    public boolean updateRound(Round round) {
        return false;
    }

    @Override
    public boolean deleteRoundById(int id) {
        return false;
    }

    @Override
    public List<Round> getRoundsForGame(int gameId) {
        return null;
    }
}
