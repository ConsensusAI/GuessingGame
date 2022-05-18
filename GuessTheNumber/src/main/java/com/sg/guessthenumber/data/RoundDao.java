package com.sg.guessthenumber.data;

import com.sg.guessthenumber.models.Round;
import com.sg.guessthenumber.models.Game;

import java.util.List;

public interface RoundDao {
    List<Round> getAllRounds();
    Round getRoundById(int id);
    Round addRound(Round round);
    boolean updateRound(Round round);
    boolean deleteRoundById(int id);

    List<Round> getRoundsForGame(int gameId);
}
