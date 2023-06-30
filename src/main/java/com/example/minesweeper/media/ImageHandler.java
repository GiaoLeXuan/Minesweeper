package com.example.minesweeper.media;

import com.example.minesweeper.MinesweeperApplication;

public class ImageHandler {
    private static final String pathToImages = "images/";

    public static String getImagePath(String fileName) {
        return String.valueOf(MinesweeperApplication.class.getResource(pathToImages + fileName));
    }
}
