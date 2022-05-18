package com.sg.guessthenumber.data;

import com.sg.guessthenumber.models.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.List;

@Repository
public class GameDatabaseDao implements GameDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GameDatabaseDao(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    @Override
    public List<Game> getAllGames() {
        final String GET_ALL_GAMES = "SELECT id, answer, finished FROM game;";
        return jdbcTemplate.query(GET_ALL_GAMES, new GameMapper());
    }

    @Override
    public Game getGameById(int id) {
        try {
            final String GET_GAME_BY_ID = "SELECT id, answer, finished " +
                    "FROM game WHERE id = ?;";
            return jdbcTemplate.queryForObject(GET_GAME_BY_ID, new GameMapper(), id);
        }  catch(DataAccessException ex) {
            return null;
        }
    }

    public Game createGame(Game game) {
        final String ADD_GAME = "INSERT INTO game(answer) " +
                "VALUES (?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {
            PreparedStatement statement = conn.prepareStatement(
                    ADD_GAME,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, game.getAnswer());
            return statement;
        }, keyHolder);

        int newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        game.setId(newId);

        return game;
    }

    @Override
    public boolean updateGame(Game game) {
        final String UPDATE_GAME = "UPDATE game SET " +
                "answer = ?," +
                "finished = ? " +
                "WHERE id = ?;";

        return jdbcTemplate.update(UPDATE_GAME,
                game.getAnswer(),
                game.isFinished(),
                game.getId()) > 0;
    }

    @Override
    @Transactional
    public boolean deleteGameById(int id) {
        final String DELETE_ROUNDS_FOR_GAME = "DELETE FROM round WHERE gameId = ?;";
        jdbcTemplate.update(DELETE_ROUNDS_FOR_GAME, id);
        final String DELETE_GAME = "DELETE FROM game WHERE id = ?;";
        return jdbcTemplate.update(DELETE_GAME, id) > 0;
    }

    private static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game game = new Game();
            game.setId(rs.getInt("id"));
            game.setAnswer(rs.getInt("answer"));
            game.setFinished(rs.getBoolean("finished"));
            return game;
        }
    }
}
