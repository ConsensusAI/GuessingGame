package com.sg.guessthenumber.service;

import com.sg.guessthenumber.data.RoundDao;
import com.sg.guessthenumber.models.Round;

import java.util.List;

public class RoundDaoDBTestStub implements RoundDao {
    @Override
    public List<Round> getAllRounds() {
        return null;
    }

    @Override
    public Round getRoundById(int id) {
        return null;
    }

    @Override
    public Round addRound(Round round) {
        return null;
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
