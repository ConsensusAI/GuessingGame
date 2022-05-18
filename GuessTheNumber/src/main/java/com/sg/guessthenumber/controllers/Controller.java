package com.sg.guessthenumber.controllers;

import com.sg.guessthenumber.data.GameDao;
import com.sg.guessthenumber.data.RoundDao;
import com.sg.guessthenumber.models.Game;
import com.sg.guessthenumber.models.Round;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api")
public class Controller {

    private final GameDao gameDao;
    private final RoundDao roundDao;

    public Controller(GameDao gameDao, RoundDao roundDao) {
        this.gameDao = gameDao;
        this.roundDao = roundDao;
    }

    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public int begin() {
        Game game = new Game();
        game.setAnswer(answerGenerator(4));
        game = gameDao.createGame(game);
        return game.getId();
    }

    @PostMapping("/guess")
    public Round guess(@RequestBody int gameId, int guess) {

        return null;
    }

    @GetMapping("/game")
    public List<Game> getAllGames() {
        return gameDao.getAllGames();
    }

    @GetMapping("/game/{gameId}")
    public Game getGameById(@PathVariable int gameId) {
        return gameDao.getGameById(gameId); // TODO: Don't show answer of unfinished game
    }

    @GetMapping("/rounds/{gameId}")
    public List<Round> getRoundsForGame(@PathVariable int gameId) {
        return roundDao.getRoundsForGame(gameId);
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
