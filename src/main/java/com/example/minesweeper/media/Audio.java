package com.example.minesweeper.media;

import com.example.minesweeper.MinesweeperApplication;

public enum Audio {

    MAIN_THEME("on_and_on.mp3"),
    EXPLODE_SOUND("small-explode-blast.wav"), LOSE_MUSIC("lose_sound.mp3"),
    WINNING_SHORT_INFORM("winning_short_inform.wav"),
    FLAG("clothes-drop.wav"),
    UNFLAG("unflag.wav"),
    END_GAME_MUSIC ("waiting.mp3");

    private static final String folderPath = "media/";

    private final String fileName;

    Audio(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileResource() {
        return String.valueOf(
                MinesweeperApplication.class.getResource(folderPath + fileName));
    }
}
