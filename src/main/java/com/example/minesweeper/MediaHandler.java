package com.example.minesweeper;

public class MediaHandler {
    private static final String pathToImages = "media/";

    public static String getMediaPath(String fileName) {
        return String.valueOf(MinesweeperApplication.class.getResource(pathToImages + fileName));
    }
}
