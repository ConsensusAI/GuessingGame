package com.sg.guessthenumber.service;

import com.sg.guessthenumber.data.GameDao;
import com.sg.guessthenumber.data.RoundDao;
import com.sg.guessthenumber.models.Round;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class GameRunnerService {

    private final GameDao gameDao;
    private final RoundDao roundDao;

    public GameRunnerService(GameDao gameDao, RoundDao roundDao) {
        this.gameDao = gameDao;
        this.roundDao = roundDao;
    }

    public Round checkGuess(Round round) {
        int guess = round.getGuess();
        int gameId = round.getGameId();
        int answer = gameDao.getGameById(gameId).getAnswer();
        String result = "";
        Map<Integer, Integer> nums = new HashMap<>();
        while (guess > 0) {
            int guessDigit = guess % 10;
            int answerDigit = answer % 10;
            nums.put(answerDigit, guessDigit);
            guess /= 10;
            answer /= 10;
        }
        guess = round.getGuess();
        answer = gameDao.getGameById(gameId).getAnswer();
        while (guess > 0) {
            int guessDigit = guess % 10;
            int answerDigit = answer % 10;
            guess /= 10;
            answer /= 10;
            if (guessDigit == answerDigit) {
                result += "e:";
                continue;
            }
            if (nums.entrySet()
                    .stream()
                    .filter(x -> x.getKey() == guessDigit)
                    .collect(Collectors.toList())
                    .size() > 0 ) {
                result += "p:";
                continue;
            }
            result += "0:";
        }

        result = result.substring(0, result.length() - 1);
        result = new StringBuilder(result).reverse().toString();
        round.setResult(result);
        return round;
    }
}

/*
Check if exists in map
if so
    Check if equal
        if equal then e for that digit
        else p for that digit
else 0 for that digit
 */