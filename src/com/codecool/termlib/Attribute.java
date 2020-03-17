package com.codecool.termlib;

public enum Attribute {
    RESET("\033[0m"),
    BRIGHT("\033[1m"),
    DIM("\033[2m"),
    UNDERSCORE("\033[4m"),
    BLINK("\033[5m"),
    REVERSE("\033[7m"),
    HIDDEN("\033[8m");


    private final String code;

    Attribute(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
