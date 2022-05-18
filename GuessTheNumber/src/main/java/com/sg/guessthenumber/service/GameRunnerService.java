package com.sg.guessthenumber.service;

import com.sg.guessthenumber.data.GameDao;
import com.sg.guessthenumber.data.RoundDao;
import com.sg.guessthenumber.models.Game;
import com.sg.guessthenumber.models.Round;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        round = roundDao.addRound(round);
        return round;
    }

    public int createGame() {
        Game game = new Game();
        game.setAnswer(answerGenerator(4));
        game = gameDao.createGame(game);
        return game.getId();
    }

    public List<Game> getAllGames() {
        return gameDao.getAllGames();
    }

    public List<Round> getRoundsForGame(int gameId) {
        return roundDao.getRoundsForGame(gameId);
    }

    public Game getGameById(int gameId) {
        return gameDao.getGameById(gameId);
    }

    private int answerGenerator(int numOfDigits) {
        int[] nums = new int[numOfDigits];
        Random random = new Random();
        for (int i = 0; i < nums.length; i++) {
            int curr = random.nextInt(10);
            boolean exists = IntStream.of(nums).anyMatch(x -> x == curr);
            if (exists) {
                i--;
            } else {
                nums[i] = curr;
            }
        }
        int factor = 1;
        int result = 0;
        for (int num : nums) {
            result += (num * factor);
            factor *= 10;
        }
        return result;
    }
}
