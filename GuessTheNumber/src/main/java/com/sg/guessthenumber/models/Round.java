package com.sg.guessthenumber.models;

import java.sql.Timestamp;
import java.util.Objects;

public class Round {
    private int id;
    private int guess;
    private Timestamp time;
    private String result;
    private int gameId;


    public int getGuess() {
        return guess;
    }

    public void setGuess(int guess) {
        this.guess = guess;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (getClass() != obj.getClass()) { return false; }
        final Round other = (Round) obj;
        if (!Objects.equals(this.getId(), other.getId()))  { return false; }
        if (!Objects.equals(this.getGameId(), other.getGameId()))  { return false; }
        if (!Objects.equals(this.getGuess(), other.getGuess()))  { return false; }
        if (!Objects.equals(this.getResult(), other.getResult()))  { return false; }
        if (!Objects.equals(this.getTime(), other.getTime()))  { return false; }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.getId());
        hash = 89 * hash + Objects.hashCode(this.getGameId());
        hash = 89 * hash + Objects.hashCode(this.getGuess());
        hash = 89 * hash + Objects.hashCode(this.getResult());
        hash = 89 * hash + Objects.hashCode(this.getTime());
        return hash;
    }
}
