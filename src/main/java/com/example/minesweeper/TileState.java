package com.example.minesweeper;

public enum TileState {
    EXPOSED("exposed.png"),
    NUMBER_ONE("number1.png"),
    NUMBER_TWO("number2.png"),
    NUMBER_THREE("number3.png"),
    NUMBER_FOUR("number4.png"),
    NUMBER_FIVE("number5.png"),
    NUMBER_SIX("number6.png"),
    NUMBER_SEVEN("number7.png"),
    NUMBER_EIGHT("number8.png"),
    MINE("mine.png"),
    HIT_MINE("hit-mine.png"),
    FLAG("flag.png"),
    BLANK("blank.png");

    private final String fileName;

    TileState(String fileName) {
        this.fileName = fileName;
    }

    public String getImagePath() {
        return ImageHandler.getImagePath(fileName);
    }

    public static final TileState[] tileStates = TileState.values();
}
