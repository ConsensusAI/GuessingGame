package com.sg.guessthenumber.data;

import com.sg.guessthenumber.models.Game;
import com.sg.guessthenumber.models.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class RoundDatabaseDao implements RoundDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RoundDatabaseDao(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    @Override
    public List<Round> getAllRounds() {
        final String GET_ALL_ROUNDS = "SELECT id, guess, time, result, gameId " +
                "FROM round;";
        return jdbcTemplate.query(GET_ALL_ROUNDS, new RoundMapper());
    }

    @Override
    public Round getRoundById(int id) {
        final String GET_ROUND_BY_ID = "SELECT id, guess, time, result, gameId " +
                "FROM round WHERE id = ?";
        return jdbcTemplate.queryForObject(GET_ROUND_BY_ID, new RoundMapper(), id);
    }

    @Override
    public Round addRound(Round round) {
        final String ADD_ROUND = "INSERT INTO round(guess, time, result, gameId) " +
                "VALUES (?,?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {
            PreparedStatement statement = conn.prepareStatement(
                    ADD_ROUND,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, round.getGuess());
            statement.setTimestamp(2, round.getTime());
            statement.setString(3, round.getResult());
            statement.setInt(4, round.getGameId());
            return statement;
        }, keyHolder);

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
    public List<Round> getRoundsForGame(Game game) {
        return null;
    }

    private static final class RoundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            Round round = new Round();
            round.setGuess(rs.getInt("guess"));
            round.setGameId(rs.getInt("gameId"));
            round.setTime(rs.getTimestamp("time"));
            round.setResult(rs.getString("result"));
            return round;
        }
    }
}
