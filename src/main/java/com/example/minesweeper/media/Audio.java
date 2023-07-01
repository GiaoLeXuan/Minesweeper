package com.example.minesweeper.media;

public enum Audio {

    MAIN_THEME("mixkit-feeling-happy-5.mp3"),
    EXPLODE_SOUND("small-explode-blast.wav"),

    WIN_MUSIC("win_sound.mp3"),

    LOSE_MUSIC("lose_sound.mp3");

    private final String fileName;

    Audio(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
