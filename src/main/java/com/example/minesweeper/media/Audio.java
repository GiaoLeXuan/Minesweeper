package com.example.minesweeper.media;

public enum Audio {

    MAIN_THEME("mixkit-feeling-happy-5.mp3"),
    EXPLODE_SOUND("small-explode-blast.wav");

    private final String fileName;

    Audio(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
