package com.sg.guessthenumber.models;

import java.util.List;
import java.util.Objects;

public class Game {
    private int id;
    private int answer;
    private boolean isFinished;

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (getClass() != obj.getClass()) { return false; }
        final Game other = (Game) obj;
        if (!Objects.equals(this.getAnswer(), other.getAnswer())) { return false; }
        if (!Objects.equals(this.getId(), other.getId())) { return false; }
        if (!Objects.equals(this.isFinished, other.isFinished)) {return false; }
        return true;
    }
}
