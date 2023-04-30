package com.example.minesweeper;

public enum TileState {
    BLANK("blank.png"),
    EXPOSED("exposed.png"),
    FLAG("flag.png"),
    MINE("mine.png"),
    NUMBER_ONE("number1.png"),
    NUMBER_TWO("number2.png"),
    NUMBER_THREE("number3.png"),
    NUMBER_FOUR("number4.png"),
    NUMBER_FIVE("number5.png"),
    NUMBER_SIX("number6.png"),
    NUMBER_SEVEN("number7.png"),
    NUMBER_EIGHT("number8.png");

    private final String fileName;

    private static final String pathToImages = "images/";

    TileState(String fileName) {
        this.fileName = fileName;
    }

    public String getImagePath() {
        return String.valueOf(MinesweeperApplication.class.getResource(pathToImages + fileName));
    }
}
