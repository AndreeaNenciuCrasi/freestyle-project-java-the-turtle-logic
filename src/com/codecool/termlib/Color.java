package com.codecool.termlib;

public enum Color {

    BLACK("\33[30m"),
    RED("\33[31m"),
    GREEN("\33[32m"),
    YELLOW("\33[33m"),
    BLUE("\33[34m"),
    MAGENTA("\033[35m"),
    CYAN("\033[36m"),
    WHITE("\033[37m"),
    BG_BLACK("\033[40m"),
    BG_RED("\033[41m"),
    BG_GREEN("\033[42m"),
    BG_YELLOW("\033[43m"),
    BG_BLUE("\033[44m"),
    BG_PURPLE("\033[45m"),
    BG_CYAN("\033[46m"),
    BG_WHITE("\033[47m");


    private final String code;

    Color(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }

}