package com.sg.guessthenumber.data;

import com.sg.guessthenumber.models.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class RoundDaoDB implements RoundDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RoundDaoDB(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    @Override
    public List<Round> getAllRounds() {
        final String GET_ALL_ROUNDS = "SELECT id, guess, time, result, gameId " +
                "FROM round;";
        return jdbcTemplate.query(GET_ALL_ROUNDS, new RoundMapper());
    }

    @Override
    public Round getRoundById(int id) {
        try {
            final String GET_ROUND_BY_ID = "SELECT id, guess, time, result, gameId " +
                    "FROM round WHERE id = ?";
            return jdbcTemplate.queryForObject(GET_ROUND_BY_ID, new RoundMapper(), id);
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    public Round addRound(Round round) {

        final String ADD_ROUND = "INSERT INTO round(guess, result, gameId) " +
                "VALUES (?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {
            PreparedStatement statement = conn.prepareStatement(
                    ADD_ROUND,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, round.getGuess());
            statement.setString(2, round.getResult());
            statement.setInt(3, round.getGameId());
            return statement;
        }, keyHolder);

        int newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        round.setId(newId);

        Timestamp newTime = jdbcTemplate.queryForObject("SELECT time FROM round " +
                "WHERE id = ?", Timestamp.class, round.getId());
        round.setTime(newTime);

        return round;
    }

    @Override
    public boolean updateRound(Round round) {
        final String UPDATE_ROUND = "UPDATE round SET " +
                "guess = ?," +
                "time = ?," +
                "result = ?," +
                "gameId = ? " +
                "WHERE id = ?";

        return jdbcTemplate.update(UPDATE_ROUND,
                round.getGuess(),
                round.getTime(),
                round.getResult(),
                round.getGameId(),
                round.getId()) > 0;
    }

    @Override
    public boolean deleteRoundById(int id) {
        final String DELETE_ROUND = "DELETE FROM round WHERE id = ?;";
        return jdbcTemplate.update(DELETE_ROUND, id) > 0;
    }

    @Override
    public List<Round> getRoundsForGame(int gameId) {
        final String GET_ROUNDS_FOR_GAME = "SELECT id, guess, time, result, gameId " +
                "FROM round WHERE gameId = ? " +
                "ORDER BY time;";
        return jdbcTemplate.query(GET_ROUNDS_FOR_GAME, new RoundMapper(), gameId);
    }

    private static final class RoundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            Round round = new Round();
            round.setGuess(rs.getInt("guess"));
            round.setGameId(rs.getInt("gameId"));
            round.setTime(rs.getTimestamp("time"));
            round.setResult(rs.getString("result"));
            round.setId(rs.getInt("id"));
            return round;
        }
    }
}
