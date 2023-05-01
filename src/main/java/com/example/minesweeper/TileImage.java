package com.example.minesweeper;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class TileImage {
    private static final TileImage tileImage = new TileImage();
    public Map<TileState, Image> imageList = new HashMap<>();

    private TileImage() {
        for (TileState tileState : TileState.tileStates) {
            imageList.put(tileState, new Image(tileState.getImagePath()));
        }
    }

    public static TileImage getInstance() {
        return tileImage;
    }

    public Map<TileState, Image> getImageList() {
        return imageList;
    }
}
