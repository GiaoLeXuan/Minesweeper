package com.example.minesweeper.media;

import com.example.minesweeper.MinesweeperApplication;

public enum Audio {

    MAIN_THEME("feeling-happy.mp3"),
    EXPLODE_SOUND("small-explode-blast.wav"),
    WINNING_SHORT_INFORM("winning_short_inform.wav"),
    FLAG("clothes-drop.wav"),
    UNFLAG("unflag.wav"),
    END_GAME_MUSIC ("waiting.mp3"),
    ONE_TILE("one.mp3"),
    MANY_TILES("multiple_tiles.mp3"),
    LARGE_TILE_GROUP("include_blank_tile.mp3");

    private static final String folderPath = "media/";

    private final String fileName;

    Audio(String fileName) {
        this.fileName = fileName;
    }

    public String getFileResource() {
        return String.valueOf(
                MinesweeperApplication.class.getResource(folderPath + fileName));
    }
}
