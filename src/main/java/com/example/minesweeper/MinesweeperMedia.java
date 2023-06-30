package com.example.minesweeper;

public enum MinesweeperMedia {

    MAIN_THEME("mixkit-feeling-happy-5.mp3"),
    EXPLODE_SOUND("small-explode-blast.wav");

    private final String fileName;

    MinesweeperMedia(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
