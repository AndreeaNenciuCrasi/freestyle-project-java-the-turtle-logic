package com.codecool.termlib;

public enum Direction {
    UP("\033[nA"),
    DOWN("\033[nB"),
    FORWARD ("\033[nC"),
    BACKWARD ("\033[nD"),
    CURSOR_POSITION("\033[{ROW};{COLUMN}f"),
    SAVE_CURSOR("\0337"),
    RESTORE_CURSOR("\0338");

    private final String code;

    Direction(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
